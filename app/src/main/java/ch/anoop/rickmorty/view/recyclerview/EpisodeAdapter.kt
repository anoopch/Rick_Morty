package ch.anoop.rickmorty.view.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import ch.anoop.rickmorty.GetEpisodesListQuery
import ch.anoop.rickmorty.R
import ch.anoop.rickmorty.view.recyclerview.diff.EpisodeDiffUtil
import ch.anoop.rickmorty.view.recyclerview.holder.EpisodeViewHolder

class EpisodeAdapter : ListAdapter<GetEpisodesListQuery.Result,
        EpisodeViewHolder>(EpisodeDiffUtil()) {

    var onItemClicked: ((GetEpisodesListQuery.Result) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recycler_item_episode,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: EpisodeViewHolder, position: Int) {
        viewHolder.binding.episode = getItem(position)

        viewHolder.binding.root.setOnClickListener {
            onItemClicked?.invoke(getItem(position))
        }

    }

}