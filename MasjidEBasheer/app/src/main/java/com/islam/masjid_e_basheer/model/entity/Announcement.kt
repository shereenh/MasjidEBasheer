package com.islam.masjid_e_basheer.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.islam.masjid_e_basheer.model.Constants

@Entity(tableName = Constants.FIRESTORE_DOC_ANNOUNCEMENT)
data class Announcement(
    @PrimaryKey var id: String,
    @ColumnInfo(name= Constants.TITLE) var title: String,
    @ColumnInfo(name= Constants.DESCRIPTION) var description: String)
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as Announcement
        if(id == other.id) return true

        return false
    }
}