package com.islam.masjid_e_basheer.model.firestore

import com.islam.masjid_e_basheer.model.Constants
import com.islam.masjid_e_basheer.model.entity.Announcement
import com.islam.masjid_e_basheer.model.entity.SimplePrayer

class FirestoreFactory {
    companion object {

        fun getAnnouncement(map : Map<String, Any>, id: String) : Announcement {
            return Announcement(id,
                map[Constants.TITLE].toString(),
                map[Constants.DESCRIPTION].toString())
        }

        fun getSimplePrayer(map : Map<String, Any>, id: String) : SimplePrayer{
            return SimplePrayer(id,
                map[Constants.FROM].toString(),
                map[Constants.TO].toString(),
                map[Constants.FAJR].toString(),
                map[Constants.DHUHR].toString(),
                map[Constants.ASR].toString(),
                "Sunset",
                map[Constants.ISHA].toString())
        }
    }
}