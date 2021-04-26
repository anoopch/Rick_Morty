package ch.anoop.rickmorty.view.recyclerview.diff

import androidx.recyclerview.widget.DiffUtil
import ch.anoop.rickmorty.GetEpisodesListQuery

class EpisodeDiffUtil : DiffUtil.ItemCallback<GetEpisodesListQuery.Result>() {

    override fun areItemsTheSame(
        thisItem: GetEpisodesListQuery.Result,
        thatItem: GetEpisodesListQuery.Result
    ): Boolean {
        return thisItem.id == thatItem.id
    }

    override fun areContentsTheSame(
        thisItem: GetEpisodesListQuery.Result,
        thatItem: GetEpisodesListQuery.Result
    ): Boolean {
        return thisItem == thatItem
    }

}