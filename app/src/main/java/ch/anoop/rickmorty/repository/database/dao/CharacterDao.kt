package ch.anoop.rickmorty.repository.database.dao

import androidx.room.Dao
import ch.anoop.rickmorty.model.Character

@Dao
interface CharacterDao {
    fun getCharactersList(pageNumber: Int): List<Character>
    fun getCharacterByID(id: String): Character
}
