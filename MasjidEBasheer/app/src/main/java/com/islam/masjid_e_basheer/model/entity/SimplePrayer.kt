package com.islam.masjid_e_basheer.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.islam.masjid_e_basheer.model.Constants

@Entity(tableName = Constants.FIRESTORE_DOC_SIMPLE_PRAYER)
class SimplePrayer (
    @PrimaryKey var id: String,
    @ColumnInfo(name = Constants.FROM) var from: String,
    @ColumnInfo(name = Constants.TO) var to: String,
    @ColumnInfo(name = Constants.FAJR) var fajr: String,
    @ColumnInfo(name = Constants.DHUHR) var dhuhr: String,
    @ColumnInfo(name = Constants.ASR) var asr: String,
    @ColumnInfo(name = Constants.MAGHRIB) var maghrib: String,
    @ColumnInfo(name = Constants.ISHA) var isha: String)
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as SimplePrayer
        if(id == other.id) return true

        return false
    }
}