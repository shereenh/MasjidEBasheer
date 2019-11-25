package com.islam.masjid_e_basheer.model.room

import android.content.Context
import com.islam.masjid_e_basheer.model.entity.Announcement
import com.islam.masjid_e_basheer.model.entity.SimplePrayer
import com.islam.masjid_e_basheer.model.room.dao.AnnouncementDao
import com.islam.masjid_e_basheer.model.room.dao.SimplePrayerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseDaos(mContext: Context) {

    protected var announcementDao: AnnouncementDao = RDatabase.getInstance(mContext)!!.announcementDao()
    protected var simplePrayerDao: SimplePrayerDao = RDatabase.getInstance(mContext)!!.simplePrayerDao()


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

    protected fun insertSimplePrayer(prayer: SimplePrayer){
        GlobalScope.launch {
            val query = async(Dispatchers.IO){
                simplePrayerDao.insertSimplePrayer(prayer)
            }
            query.await()
        }
    }

    protected fun deleteSimplePrayer(id: String){
        GlobalScope.launch {
            val query = async(Dispatchers.IO){
                simplePrayerDao.deleteSimplePrayer(id)
            }
            query.await()
        }
    }

    fun deleteRoomData(){
        GlobalScope.launch {
            val query = async(Dispatchers.IO){
                announcementDao.deleteAllAnnouncements()
                simplePrayerDao.deleteAllSimplePrayers()
            }
            query.await()
        }
    }

}