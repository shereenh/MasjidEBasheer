package com.islam.masjid_e_basheer.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.islam.masjid_e_basheer.R
import com.islam.masjid_e_basheer.view.fragments.*
import com.islam.masjid_e_basheer.view.helper.NavigationHelper
import com.islam.masjid_e_basheer.viewmodel.MainViewModel


class MainActivity : AppCompatActivity(){

    val TAG = "MainActivity"

    private lateinit var mViewModel: MainViewModel
    lateinit var mNavigationHelper: NavigationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        listeners()
        observers()
    }

    private fun init(){

        startFragment(HomeFragment())

        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mNavigationHelper = NavigationHelper(window.decorView.rootView, this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    fun startFragment(fragment: BaseFragment){
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(mNavigationHelper.optionItemSelected(item)){ return true }
        return super.onOptionsItemSelected(item)
    }

    private fun listeners(){

    }

    private fun observers(){

    }
}
