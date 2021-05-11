package com.b21cap0051.naratik.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.ActivityHomeBinding
import com.b21cap0051.naratik.ui.home.homefragment.AccountFragment
import com.b21cap0051.naratik.ui.home.homefragment.CameraFragment
import com.b21cap0051.naratik.ui.home.homefragment.ExploreFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        supportActionBar?.setDisplayShowTitleEnabled(false)
//        supportActionBar?.title = resources.getString(R.string.home)

        val bottomNav = binding.bottomBar
        bottomNav.setOnNavigationItemSelectedListener(navListener)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                ExploreFragment()
            ).commit()
        }
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.item_nav_account -> selectedFragment = AccountFragment()
                R.id.item_nav_home -> selectedFragment = ExploreFragment()
                R.id.item_nav_camera -> selectedFragment = CameraFragment()
            }
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container, selectedFragment!!
            ).commit()
            true
        }

}