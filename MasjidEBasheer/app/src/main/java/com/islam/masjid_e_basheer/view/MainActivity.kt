package com.islam.masjid_e_basheer.view

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.navigation.NavigationView
import com.islam.masjid_e_basheer.R
import com.islam.masjid_e_basheer.view.fragments.*
import com.islam.masjid_e_basheer.viewmodel.MainViewModel


class MainActivity : AppCompatActivity(){

    val TAG = "MainActivity"

    private lateinit var mViewModel: MainViewModel

    lateinit var drawerToggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView

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

        drawerLayout = findViewById(R.id.activity_main)
        drawerToggle = ActionBarDrawerToggle(this,
            drawerLayout, R.string.open, R.string.close)

        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView = findViewById(R.id.nv)

        navigationView.setNavigationItemSelectedListener(object :
            NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
                val id: Int = item.itemId
                when (id) {
                    R.id.isf_newsletter -> startFragment(NewsletterFragment())
                    R.id.prayer_times -> startFragment(PrayerFragment())
                    R.id.calendar_and_events -> startFragment(CalendarEventsFragment())
                    R.id.falah_islamic_institute -> startFragment(InstituteFragment())
                    R.id.announcements -> startFragment(AnnouncementsFragment())
                    R.id.isf_members -> startFragment(MembersFragment())
                    R.id.contact_us -> startFragment(ContactFragment())
                    R.id.donate -> startFragment(DonateFragment())
                    R.id.settings -> startFragment(SettingsFragment())
                    else -> return true
                }
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
        })
    }

    fun startFragment(fragment: BaseFragment){
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(drawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    private fun listeners(){

    }

    private fun observers(){

    }
}
