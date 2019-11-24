package com.islam.masjid_e_basheer.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.islam.masjid_e_basheer.model.Constants

@Entity(tableName = Constants.FIRESTORE_DOC_PRAYER)
class Prayer (
    @PrimaryKey var id: String,
    @ColumnInfo(name = Constants.DAY) var day: Int,
    @ColumnInfo(name = Constants.DATE) var date: Int,
    @ColumnInfo(name = Constants.LUNAR_DATE) var lunar_date: Int,
    @ColumnInfo(name = Constants.FAJR) var fajr: String,
    @ColumnInfo(name = Constants.SUNRISE) var sunrise: String,
    @ColumnInfo(name = Constants.ZOHAR) var zohar: String,
    @ColumnInfo(name = Constants.ASR_SHAFAI) var asr_s: String,
    @ColumnInfo(name = Constants.ASR_HANAFI) var asr_h: String,
    @ColumnInfo(name = Constants.MAGHRIB) var maghrib: String,
    @ColumnInfo(name = Constants.ISHA) var isha: String)
{
        override fun equals(other: Any?): Boolean {
            if (this === other) return true

            other as Prayer
            if(id == other.id) return true

            return false
        }
}

