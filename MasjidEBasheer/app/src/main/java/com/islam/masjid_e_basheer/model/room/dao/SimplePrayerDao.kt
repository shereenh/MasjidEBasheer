package com.islam.masjid_e_basheer.model.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.islam.masjid_e_basheer.model.Constants
import com.islam.masjid_e_basheer.model.entity.SimplePrayer

@Dao
interface SimplePrayerDao {

    @Insert(onConflict = REPLACE)
    fun insertSimplePrayer(prayer: SimplePrayer)

    @Query("SELECT * FROM " + Constants.FIRESTORE_DOC_SIMPLE_PRAYER)
    fun getAllSimplePrayers(): LiveData<List<SimplePrayer>>

    @Query("DELETE FROM " + Constants.FIRESTORE_DOC_SIMPLE_PRAYER)
    fun deleteAllSimplePrayers()

    @Query("DELETE FROM " + Constants.FIRESTORE_DOC_SIMPLE_PRAYER + " WHERE id=:id")
    fun deleteSimplePrayer(id: String)
}