package com.islam.masjid_e_basheer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.islam.masjid_e_basheer.model.DataRepository
import com.islam.masjid_e_basheer.model.entity.Announcement
import com.islam.masjid_e_basheer.model.entity.SimplePrayer

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: DataRepository = DataRepository.getInstance(
                                                application.getSharedPreferences("prefsFile", 0),
                                                application.applicationContext
                                                )

    val announcementLiveData: LiveData<List<Announcement>>
    val simplePrayerLiveData: LiveData<List<SimplePrayer>>

    init {
        announcementLiveData = mRepository.announcements
        simplePrayerLiveData = mRepository.simplePrayers
    }
}
