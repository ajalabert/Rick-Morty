package com.ynov.kotlin.rickmorty.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.list.fragment.EpisodesFragment
import com.ynov.kotlin.rickmorty.presentation.list.fragment.CharactersFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val listFragment = CharactersFragment()
    private val episodesFragment = EpisodesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_characters -> loadFragment(listFragment)
                else -> loadFragment(episodesFragment)
            }
            true
        }

        bottom_navigation.selectedItemId = R.id.menu_characters
    }

    private fun loadFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction()
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
