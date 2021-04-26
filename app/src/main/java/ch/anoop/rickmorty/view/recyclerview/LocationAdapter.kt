package ch.anoop.rickmorty.view.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import ch.anoop.rickmorty.GetLocationsListQuery
import ch.anoop.rickmorty.R
import ch.anoop.rickmorty.view.recyclerview.diff.LocationDiffUtil
import ch.anoop.rickmorty.view.recyclerview.holder.LocationViewHolder

class LocationAdapter: ListAdapter<GetLocationsListQuery.Result,
        LocationViewHolder>(LocationDiffUtil()) {

    var onItemClicked: ((GetLocationsListQuery.Result) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recycler_item_location,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: LocationViewHolder, position: Int) {
        viewHolder.binding.location = getItem(position)

        viewHolder.binding.root.setOnClickListener {
            onItemClicked?.invoke(getItem(position))
        }

    }

}