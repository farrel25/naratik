package com.b21cap0051.naratik.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.ActivityHomeBinding
import com.b21cap0051.naratik.databinding.CustomActionBarLogoFavoriteBinding
import com.b21cap0051.naratik.ui.favourite.FavouriteActivity
import com.b21cap0051.naratik.ui.home.homefragment.AccountFragment
import com.b21cap0051.naratik.ui.home.homefragment.CameraFragment
import com.b21cap0051.naratik.ui.home.homefragment.ExploreFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var bindingActionBar: CustomActionBarLogoFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        bindingActionBar = CustomActionBarLogoFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomBarMenu.background = null
        binding.bottomBarMenu.menu.getItem(1).isEnabled = false

        val bottomNav = binding.bottomBarMenu
        bottomNav.setOnNavigationItemSelectedListener(navListener)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                ExploreFragment()
            ).commit()
        }

        bindingActionBar.btnFavorite.setOnClickListener{
            val intent = Intent(this@HomeActivity, FavouriteActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.item_nav_account -> selectedFragment = AccountFragment()
                R.id.item_nav_home -> selectedFragment = ExploreFragment()
            }
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container, selectedFragment!!
            ).commit()
            true
        }


}