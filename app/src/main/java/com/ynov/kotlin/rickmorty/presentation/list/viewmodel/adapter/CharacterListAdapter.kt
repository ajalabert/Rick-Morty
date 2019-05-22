package com.ynov.kotlin.rickmorty.presentation.list.viewmodel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.entity.models.Character
import kotlinx.android.synthetic.main.view_character_list_item.view.*

class CharacterListAdapter : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>(){

    private var characters: MutableList<Character> = mutableListOf()
    var onItemClick: ((Character) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.view_character_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int {
        return characters.count()
    }

    fun updateList(characters: List<Character>){
        this.characters.clear()
        this.characters.addAll(characters)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                this@CharacterListAdapter.onItemClick?.invoke(characters[adapterPosition])
            }
        }

        fun bind(character: Character) {
            itemView.view_character_list_item_name.text = character.name
            Picasso.get().load(character.image).into(itemView.view_character_list_item_image)
        }
    }
}