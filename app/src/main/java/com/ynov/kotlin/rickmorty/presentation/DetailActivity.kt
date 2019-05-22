package com.ynov.kotlin.rickmorty.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ynov.kotlin.rickmorty.R
import android.view.MenuItem
import com.ynov.kotlin.rickmorty.presentation.detail.viewmodel.fragment.DetailFragment
import com.ynov.kotlin.rickmorty.presentation.list.viewmodel.fragment.ListFragment


class DetailActivity : AppCompatActivity() {

   companion object {
       const val characterIdKey = "characterId"
       fun newIntent(context: Context, id: Long): Intent =
               Intent(context, DetailActivity::class.java)
                   .putExtra(characterIdKey, id)
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val characterId = intent.getLongExtra(characterIdKey, -1)
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
