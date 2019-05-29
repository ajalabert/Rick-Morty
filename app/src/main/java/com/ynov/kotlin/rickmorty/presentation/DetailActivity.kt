package com.ynov.kotlin.rickmorty.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ynov.kotlin.rickmorty.R
import android.view.MenuItem
import com.ynov.kotlin.rickmorty.presentation.detail.fragment.DetailFragment


class DetailActivity : AppCompatActivity() {

   companion object {
       // TODO pour les constante, la bonne pratique des noms c'est plutôt CHARACTER_ID_KEY
       //  et puisque c'est un contante utilisée seulement dans ce fichier,
       //  on peut la mettre en private au dessus de la déclaration de la classe
       const val characterIdKey = "characterId"
       fun newIntent(context: Context, id: Int): Intent =
               Intent(context, DetailActivity::class.java)
                   .putExtra(characterIdKey, id)
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val characterId = intent.getIntExtra(characterIdKey, -1)
        supportFragmentManager.beginTransaction()
            .replace(R.id.detail_activity_fragment_container, DetailFragment(characterId))
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
