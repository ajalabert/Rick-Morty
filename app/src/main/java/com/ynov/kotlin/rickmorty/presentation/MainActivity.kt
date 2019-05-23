package com.ynov.kotlin.rickmorty.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.list.viewmodel.fragment.EpisodesFragment
import com.ynov.kotlin.rickmorty.presentation.list.viewmodel.fragment.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(ListFragment())

        val bottomNavigation = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_characters ->
                    loadFragment(ListFragment())
                else ->
                    loadFragment(EpisodesFragment())
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) = supportFragmentManager.beginTransaction()
        .replace(R.id.main_activity_fragment_container, fragment)
        .commit()

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Quitter")
            .setMessage("Voulez-vous fermer l'application ?")
            .setPositiveButton("Oui"
            ) { dialog, _ ->
                dialog.dismiss()
                finish()
            }
            .setNegativeButton("Non") { dialog, _ -> dialog.dismiss()
        }
            .show()
    }
}
