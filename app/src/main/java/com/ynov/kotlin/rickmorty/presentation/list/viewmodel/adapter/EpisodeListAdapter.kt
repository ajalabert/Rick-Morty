package com.ynov.kotlin.rickmorty.presentation.list.viewmodel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.entity.models.Episode
import kotlinx.android.synthetic.main.view_episode_list_item.view.*

class EpisodeListAdapter : RecyclerView.Adapter<EpisodeListAdapter.ViewHolder>() {

    private var episodes: MutableList<Episode> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_episode_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    override fun getItemCount(): Int {
        return episodes.count()
    }

    fun updateList(episodes: List<Episode>) {
        this.episodes.addAll(episodes)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(episode: Episode) {
            itemView.view_episode_list_item_name.text = "${episode.episode} - ${episode.name}"
        }
    }
}