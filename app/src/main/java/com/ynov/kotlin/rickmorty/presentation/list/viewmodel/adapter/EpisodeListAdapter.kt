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
    var onItemClick: ((Episode) -> Unit)? = null

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
        this.episodes.clear()
        this.episodes.addAll(episodes)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                this@EpisodeListAdapter.onItemClick?.invoke(episodes[adapterPosition])
            }
        }

        fun bind(episode: Episode) {
            itemView.view_episode_list_item_name.text = episode.name
        }
    }
}