package ch.anoop.rickmorty.repository.database.dao

import androidx.room.Dao
import ch.anoop.rickmorty.model.Location

@Dao
interface LocationDao {

    fun getLocationsList(pageNumber: Int): List<Location>
    fun getLocationByID(id: String): Location

}
