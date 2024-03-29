package com.islam.masjid_e_basheer.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.islam.masjid_e_basheer.model.Constants
import com.islam.masjid_e_basheer.model.entity.Announcement
import com.islam.masjid_e_basheer.model.entity.SimplePrayer
import com.islam.masjid_e_basheer.model.room.dao.AnnouncementDao
import com.islam.masjid_e_basheer.model.room.dao.SimplePrayerDao

@Database(entities = arrayOf(Announcement::class, SimplePrayer::class), version = 3, exportSchema = false)
abstract class RDatabase: RoomDatabase() {

    abstract fun announcementDao(): AnnouncementDao
    abstract fun simplePrayerDao(): SimplePrayerDao


    companion object {
        private var INSTANCE: RDatabase? = null

        fun getInstance(context: Context): RDatabase? {
            if (INSTANCE == null){
                synchronized(RDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        RDatabase::class.java,
                        Constants.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}