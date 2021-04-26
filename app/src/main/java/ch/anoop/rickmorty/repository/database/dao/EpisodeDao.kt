package ch.anoop.rickmorty.repository.database.dao

import androidx.room.Dao
import ch.anoop.rickmorty.model.Episode

@Dao
interface EpisodeDao {

    fun getEpisodesList(pageNumber: Int): List<Episode>
    fun getEpisodeByID(id: String): Episode

}