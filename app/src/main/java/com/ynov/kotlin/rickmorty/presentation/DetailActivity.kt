package com.ynov.kotlin.rickmorty.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ynov.kotlin.rickmorty.R

class DetailActivity : AppCompatActivity() {

   companion object {
       fun newIntent(context: Context, id: Long) =
               Intent(context, DetailActivity::class.java)
                   .putExtra("characterId", id)
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}
