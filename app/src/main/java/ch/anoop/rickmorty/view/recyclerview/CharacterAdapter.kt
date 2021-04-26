package ch.anoop.rickmorty.view.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import ch.anoop.rickmorty.GetCharactersListQuery
import ch.anoop.rickmorty.R
import ch.anoop.rickmorty.view.recyclerview.diff.CharacterDiffUtil
import ch.anoop.rickmorty.view.recyclerview.holder.CharacterViewHolder


class CharacterAdapter : ListAdapter<GetCharactersListQuery.Result,
        CharacterViewHolder>(CharacterDiffUtil()) {

    var onItemClicked: ((GetCharactersListQuery.Result) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recycler_item_character,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: CharacterViewHolder, position: Int) {
        viewHolder.binding.character = getItem(position)

        viewHolder.binding.root.setOnClickListener {
            onItemClicked?.invoke(getItem(position))
        }

    }
}
