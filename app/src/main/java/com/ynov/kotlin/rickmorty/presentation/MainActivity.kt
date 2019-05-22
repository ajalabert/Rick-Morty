package com.ynov.kotlin.rickmorty.presentation

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.list.viewmodel.fragment.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity_fragment_container, ListFragment())
            .commit()
    }

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
