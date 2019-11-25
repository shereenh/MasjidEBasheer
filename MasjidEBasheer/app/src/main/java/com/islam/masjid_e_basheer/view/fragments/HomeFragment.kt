package com.islam.masjid_e_basheer.view.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.islam.masjid_e_basheer.R
import com.islam.masjid_e_basheer.view.adapters.AnnouncementAdapter
import com.islam.masjid_e_basheer.view.adapters.SimplePrayerAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.prayer_table.view.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() {

    lateinit var mRootView: View
    lateinit var mAnnouncementAdapter: AnnouncementAdapter
    lateinit var mSimplePrayerAdapter: SimplePrayerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(R.layout.fragment_home, container, false)
        init()
        observers()
        return mRootView
    }

    private fun init(){

        mRootView.announcementRecycler.layoutManager = LinearLayoutManager(activity)
        mRootView.announcementRecycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        mAnnouncementAdapter = AnnouncementAdapter()
        mRootView.announcementRecycler.adapter = mAnnouncementAdapter

        mRootView.prayer.simplePrayerTableRecycler.layoutManager = LinearLayoutManager(activity)
        mSimplePrayerAdapter = SimplePrayerAdapter()
        mRootView.prayer.simplePrayerTableRecycler.adapter = mSimplePrayerAdapter


    }

    private fun observers(){
        mViewModel.announcementLiveData.observe(this, Observer {
            for(item in it){
                Log.d("Lopside", "value: " + item.title)
            }
            mAnnouncementAdapter.setData(it)
        })

        mViewModel.simplePrayerLiveData.observe(this, Observer {
            for(item in it){
                Log.d("Lopside", "value: " + item.from)
                mSimplePrayerAdapter.setData(it)
            }
        })
    }
}
