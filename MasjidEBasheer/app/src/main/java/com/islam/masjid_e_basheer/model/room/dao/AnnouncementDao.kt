package com.islam.masjid_e_basheer.model.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.islam.masjid_e_basheer.model.Constants
import com.islam.masjid_e_basheer.model.entity.Announcement

@Dao
interface AnnouncementDao {

    @Insert(onConflict = REPLACE)
    fun insertAnnouncement(announcement: Announcement)

    @Query("SELECT * FROM " + Constants.FIRESTORE_DOC_ANNOUNCEMENT)
    fun getAllAnnouncements(): LiveData<List<Announcement>>

    @Query("DELETE FROM " + Constants.FIRESTORE_DOC_ANNOUNCEMENT)
    fun deleteAllAnnouncements()

    @Query("DELETE FROM " + Constants.FIRESTORE_DOC_ANNOUNCEMENT + " WHERE id=:id")
    fun deleteAnnouncement(id: String)

}