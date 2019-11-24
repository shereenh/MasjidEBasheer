package com.islam.masjid_e_basheer.model

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.*
import com.islam.masjid_e_basheer.model.Constants.Companion.TAG
import com.islam.masjid_e_basheer.model.entity.Announcement
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

    private var newList = arrayListOf<Any>()
    private var oldList = arrayListOf<Any>()
    private var observedAnnouncementList = arrayListOf<Announcement>()

    init {
        announcements = announcementDao.getAllAnnouncements()

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

    enum class FDOCUMENT {
        ANNOUNCEMENT,
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
        }
        syncLogic()
    }

    private fun clearLists(){
        newList.clear()
        oldList.clear()
    }

    private fun syncLogic(){
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
    }

    // ROOM

    private fun insertData(data: Any){
        if(data is Announcement){
            insertAnnouncement(data)
        }
    }

    private fun deleteData(data: Any){
        if(data is Announcement){
            deleteAnnouncement(data.id)
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
    }


}