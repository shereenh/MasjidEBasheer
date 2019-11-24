package com.islam.masjid_e_basheer.model.room

import android.content.Context
import com.islam.masjid_e_basheer.model.entity.Announcement
import com.islam.masjid_e_basheer.model.room.dao.AnnouncementDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseDaos(mContext: Context) {

    protected var announcementDao: AnnouncementDao = RDatabase.getInstance(mContext)!!.announcementDao()


    protected fun insertAnnouncement(announcement: Announcement){
        GlobalScope.launch {
            val query = async(Dispatchers.IO){
                announcementDao.insertAnnouncement(announcement)
            }
            query.await()
        }
    }

    protected fun deleteAnnouncement(id: String){
        GlobalScope.launch {
            val query = async(Dispatchers.IO){
                announcementDao.deleteAnnouncement(id)
            }
            query.await()
        }
    }

    fun deleteRoomData(){
        GlobalScope.launch {
            val query = async(Dispatchers.IO){
                announcementDao.deleteAllAnnouncements()
            }
            query.await()
        }
    }

}