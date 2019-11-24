package com.islam.masjid_e_basheer.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.islam.masjid_e_basheer.R
import com.islam.masjid_e_basheer.viewmodel.MainViewModel

/**
 * A simple [Fragment] subclass.
 */
abstract class BaseFragment : Fragment() {

    lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = activity?.run{ ViewModelProviders.of(this).get(MainViewModel::class.java)}?: throw Exception("Invalid Activity")
    }
}
