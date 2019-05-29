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

    // TODO attention c'est très lourd pour la RAM de créer des fragments comme ça dès le démarrage de l'activity
    //  de plus quand les fragments sont détruits par le système, vous allez avoir des problèmes avec ces valeurs
    //  le mieux est de les créer dès qu'on veut les afficher dans les transactions du fragmentManager
    //  et de ne JAMAIS les garder en instance de classe.
    //  Si vous avez fait ça pour garder l'état des fragments, le viewmodel de chaque fragment est justement fait pour ça,
    //  mais en les gardant en instance quelque part et en les réinjectant dans les fragments dès qu'on les recréer.
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
