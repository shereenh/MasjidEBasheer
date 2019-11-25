package com.islam.masjid_e_basheer.view.helper

import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.NonNull
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.islam.masjid_e_basheer.R
import com.islam.masjid_e_basheer.view.MainActivity
import com.islam.masjid_e_basheer.view.fragments.*

class NavigationHelper(view: View, context: MainActivity) {
    var drawerToggle: ActionBarDrawerToggle
    var drawerLayout: DrawerLayout = view.findViewById(R.id.activity_main)
    private var navigationView: NavigationView

    init {
        drawerToggle = ActionBarDrawerToggle(context,
            drawerLayout, R.string.open, R.string.close)

        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        navigationView = view.findViewById(R.id.navigationView)

        navigationView.setNavigationItemSelectedListener(object :
            NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.isf_newsletter -> context.startFragment(NewsletterFragment())
                    R.id.prayer_times -> context.startFragment(PrayerFragment())
                    R.id.calendar_and_events -> context.startFragment(CalendarEventsFragment())
                    R.id.falah_islamic_institute -> context.startFragment(InstituteFragment())
                    R.id.announcements -> context.startFragment(AnnouncementsFragment())
                    R.id.isf_members -> context.startFragment(MembersFragment())
                    R.id.contact_us -> context.startFragment(ContactFragment())
                    R.id.donate -> context.startFragment(DonateFragment())
                    R.id.settings -> context.startFragment(SettingsFragment())
                    else -> return true
                }
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
        })

        val header: LinearLayout = navigationView.getHeaderView(0) as LinearLayout

        header.setOnClickListener{
            context.startFragment(HomeFragment())
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    fun optionItemSelected(item: MenuItem?): Boolean{
         return drawerToggle.onOptionsItemSelected(item)
    }
}