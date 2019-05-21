package com.ynov.kotlin.rickmorty.presentation.list.viewmodel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.entity.models.Character
import kotlinx.android.synthetic.main.view_character_list_item.view.*

class CharacterListAdapter : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>(){

    private var characters: MutableList<Character> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.view_character_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters.get(position))
    }

    override fun getItemCount(): Int {
        return characters.count()
    }

    fun updateList(characters: List<Character>){
        this.characters.clear()
        this.characters.addAll(characters)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(character: Character) {
            itemView.view_character_list_item_name.text = character.name
        }
    }
}