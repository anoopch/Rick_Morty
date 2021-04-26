package ch.anoop.rickmorty.view.recyclerview.diff

import androidx.recyclerview.widget.DiffUtil
import ch.anoop.rickmorty.GetLocationsListQuery

class LocationDiffUtil : DiffUtil.ItemCallback<GetLocationsListQuery.Result>() {

    override fun areItemsTheSame(
        thisItem: GetLocationsListQuery.Result,
        thatItem: GetLocationsListQuery.Result
    ): Boolean {
        return thisItem.id == thatItem.id
    }

    override fun areContentsTheSame(
        thisItem: GetLocationsListQuery.Result,
        thatItem: GetLocationsListQuery.Result
    ): Boolean {
        return thisItem == thatItem
    }

}