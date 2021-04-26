package ch.anoop.rickmorty.repository.database

import ch.anoop.rickmorty.model.Character
import ch.anoop.rickmorty.model.Episode
import ch.anoop.rickmorty.model.Location

interface LocalDataSource {

    suspend fun getCharactersList(pageNumber: Int): List<Character>
    suspend fun getCharacterByID(id: String): Character

    suspend fun getEpisodesList(pageNumber: Int): List<Episode>
    suspend fun getEpisodeByID(id: String): Episode

    suspend fun getLocationsList(pageNumber: Int): List<Location>
    suspend fun getLocationByID(id: String): Location
}