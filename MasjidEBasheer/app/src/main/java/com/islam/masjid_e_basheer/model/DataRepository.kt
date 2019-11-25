package com.islam.masjid_e_basheer.model

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.*
import com.islam.masjid_e_basheer.model.Constants.Companion.TAG
import com.islam.masjid_e_basheer.model.entity.Announcement
import com.islam.masjid_e_basheer.model.entity.SimplePrayer
import com.islam.masjid_e_basheer.model.firestore.FirestoreFactory
import com.islam.masjid_e_basheer.model.firestore.FirestoreRepository
import com.islam.masjid_e_basheer.model.room.BaseDaos

class DataRepository(private val prefs : SharedPreferences,
                     mContext: Context)
    : BaseDaos(mContext)
    , FirestoreRepository.FirestoreCallback{

    var mFirestoreRepository : FirestoreRepository =
        FirestoreRepository(this)

//    private var announcementDao: AnnouncementDao = RDatabase.getInstance(mContext)!!.announcementDao()
    var announcements: LiveData<List<Announcement>>
    var simplePrayers: LiveData<List<SimplePrayer>>

    private var newList = arrayListOf<Any>()
    private var oldList = arrayListOf<Any>()
    private var observedAnnouncementList = arrayListOf<Announcement>()
    private var observedSimplePrayerList = arrayListOf<SimplePrayer>()

    init {
        announcements = announcementDao.getAllAnnouncements()
        simplePrayers = simplePrayerDao.getAllSimplePrayers()

        setAllRoomData()
    }

    companion object {
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(prefs: SharedPreferences,
                        context: Context): DataRepository
        {
            return instance ?: synchronized(this) {
                instance ?: DataRepository(prefs, context)
            }
        }
    }

    override fun announcementsReceived(snapshot: QuerySnapshot) {
//        Log.d(TAG, "rec: " + snapshot.documents.toString())
        clearLists()
        for(change in snapshot.documentChanges) {
            val snap = change.document
            when(change.type){
                DocumentChange.Type.ADDED ->
                    {   Log.d(TAG, "ADDED: " + FirestoreFactory.getAnnouncement(snap.data, snap.id))
                        val announcement = FirestoreFactory.getAnnouncement(snap.data, snap.id)
                        newList.add(announcement) }
            }
        }
        Log.d(TAG, "done")
        addToOldList(FDOCUMENT.ANNOUNCEMENT)
    }

    override fun simplePrayersReceived(snapshot: QuerySnapshot) {
        Log.d(TAG, "rec: " + snapshot.documents.toString())
        clearLists()
        for(change in snapshot.documentChanges) {
            val snap = change.document
            when(change.type){
                DocumentChange.Type.ADDED ->
                {   Log.d(TAG, "ADDED: " + FirestoreFactory.getSimplePrayer(snap.data, snap.id))
                    val simplePrayer = FirestoreFactory.getSimplePrayer(snap.data, snap.id)
                    newList.add(simplePrayer) }
            }
        }
        Log.d(TAG, "done")
        addToOldList(FDOCUMENT.SIMPLE_PRAYER)
    }

    enum class FDOCUMENT {
        ANNOUNCEMENT,
        SIMPLE_PRAYER,
        PRAYER,
        OTHER
    }

    private fun addToOldList(docType: FDOCUMENT){
        when(docType){
            FDOCUMENT.ANNOUNCEMENT -> {
                for(value in observedAnnouncementList){
                    oldList.add(value)
                }
            }
            FDOCUMENT.SIMPLE_PRAYER -> {
                for(value in observedSimplePrayerList){
                    oldList.add(value)
                }
            }
        }
        syncLogic()
    }

    private fun clearLists(){
        newList = arrayListOf<Any>()
        oldList = arrayListOf<Any>()
    }

    private fun syncLogic(){
        Log.d(TAG+"k", "oldList: ")
        for(item in oldList){
            Log.d(TAG+"k", item.toString())
        }

        Log.d(TAG+"k", "newList: ")
        for(item in newList){
            Log.d(TAG+"k", item.toString())
        }


        for(value in newList){
            if(value in oldList){
                oldList.remove(value)
            } else {
                insertData(value)
            }
        }

        for(value in oldList){
            deleteData(value)
        }

        clearLists()
    }

    // ROOM

    private fun insertData(data: Any){
        if(data is Announcement){
            insertAnnouncement(data)
        } else if( data is SimplePrayer){
            insertSimplePrayer(data)
        }
    }

    private fun deleteData(data: Any){
        if(data is Announcement){
            deleteAnnouncement(data.id)
        } else if(data is SimplePrayer){
            deleteSimplePrayer(data.id)
        }
    }

    private fun setAllRoomData() {
        announcements.observeForever {
            it?.let {
                for(value in it){
                    observedAnnouncementList.add(value)
                }
            }
        }

        simplePrayers.observeForever {
            it?.let {
                for(value in it){
                    observedSimplePrayerList.add(value)
                }
            }
        }
    }


}