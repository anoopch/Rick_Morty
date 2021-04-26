package ch.anoop.rickmorty.repository.database

import ch.anoop.rickmorty.model.Character
import ch.anoop.rickmorty.model.Episode
import ch.anoop.rickmorty.model.Location
import ch.anoop.rickmorty.repository.database.dao.CharacterDao
import ch.anoop.rickmorty.repository.database.dao.EpisodeDao
import ch.anoop.rickmorty.repository.database.dao.LocationDao
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val characterDao: CharacterDao,
    private val episodeDao: EpisodeDao,
    private val locationDao: LocationDao
) : LocalDataSource {

    override suspend fun getCharactersList(pageNumber: Int): List<Character> {
        return characterDao.getCharactersList(pageNumber)
    }

    override suspend fun getCharacterByID(id: String): Character {
        return characterDao.getCharacterByID(id)
    }

    override suspend fun getEpisodesList(pageNumber: Int): List<Episode> {
        return episodeDao.getEpisodesList(pageNumber)
    }

    override suspend fun getEpisodeByID(id: String): Episode {
        return episodeDao.getEpisodeByID(id)
    }

    override suspend fun getLocationsList(pageNumber: Int): List<Location> {
        return locationDao.getLocationsList(pageNumber)
    }

    override suspend fun getLocationByID(id: String): Location {
        return locationDao.getLocationByID(id)
    }
}