package com.islam.masjid_e_basheer.model.firestore

import com.islam.masjid_e_basheer.model.Constants
import com.islam.masjid_e_basheer.model.entity.Announcement

class FirestoreFactory {
    companion object {

        fun getAnnouncement(map : Map<String, Any>, id: String) : Announcement {
            return Announcement(id,
                map[Constants.TITLE].toString(),
                map[Constants.DESCRIPTION].toString())
        }
    }
}