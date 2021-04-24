package ch.anoop.rickmorty.view.recyclerview.diff

import androidx.recyclerview.widget.DiffUtil
import ch.anoop.rickmorty.GetCharactersListQuery

class CharacterDiffUtil : DiffUtil.ItemCallback<GetCharactersListQuery.Result>() {

    override fun areItemsTheSame(
        thisItem: GetCharactersListQuery.Result,
        thatItem: GetCharactersListQuery.Result
    ): Boolean {
        return thisItem.id == thatItem.id
    }

    override fun areContentsTheSame(
        thisItem: GetCharactersListQuery.Result,
        thatItem: GetCharactersListQuery.Result
    ): Boolean {
        return thisItem == thatItem
    }

}