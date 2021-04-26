package ch.anoop.rickmorty.repository.database

import ch.anoop.rickmorty.*
import ch.anoop.rickmorty.repository.database.dao.CharacterDoa
import ch.anoop.rickmorty.repository.database.dao.EpisodeDoa
import ch.anoop.rickmorty.repository.database.dao.LocationDoa
import com.apollographql.apollo.api.Response
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val characterDoa: CharacterDoa,
    private val episodeDoa: EpisodeDoa,
    private val locationDoa: LocationDoa
) : LocalDataSource {

    override suspend fun getCharactersList(pageNumber: Int): List<Any> {
        return characterDoa.getCharactersList(pageNumber)
    }

    override suspend fun getCharacterByID(id: String): Any {
        return characterDoa.getCharacterByID(id)
    }

    override suspend fun getEpisodesList(pageNumber: Int):  List<Any> {
        return episodeDoa.getEpisodesList(pageNumber)
    }

    override suspend fun getEpisodeByID(id: String): Any {
        return episodeDoa.getEpisodeByID(id)
    }

    override suspend fun getLocationsList(pageNumber: Int):  List<Any> {
        return locationDoa.getLocationsList(pageNumber)
    }

    override suspend fun getLocationByID(id: String): Any {
        return locationDoa.getLocationByID(id)
    }
}