package ch.anoop.rickmorty.repository.network

import ch.anoop.rickmorty.*
import com.apollographql.apollo.api.Response

interface NetworkRepository {
    suspend fun getCharactersList(): Response<GetCharactersListQuery.Data>
    suspend fun getCharacterByID(id: String): Response<GetCharacterByIDQuery.Data>

    suspend fun getEpisodesList(): Response<GetEpisodesListQuery.Data>
    suspend fun getEpisodeByID(id: String): Response<GetEpisodeByIDQuery.Data>

    suspend fun getLocationsList(): Response<GetLocationsListQuery.Data>
    suspend fun getLocationByID(id: String): Response<GetLocationByIDQuery.Data>
}