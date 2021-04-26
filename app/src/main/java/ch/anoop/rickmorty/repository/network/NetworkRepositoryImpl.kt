package ch.anoop.rickmorty.repository.network

import ch.anoop.rickmorty.*
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val networkService: RickMortyAPI
) : NetworkRepository {
    override suspend fun getCharactersList(): Response<GetCharactersListQuery.Data> {
        return networkService.getAPIClient().query(GetCharactersListQuery()).await()
    }

    override suspend fun getCharacterByID(id: String): Response<GetCharacterByIDQuery.Data> {
        return networkService.getAPIClient().query(GetCharacterByIDQuery(id)).await()
    }

    override suspend fun getEpisodesList(): Response<GetEpisodesListQuery.Data> {
        return networkService.getAPIClient().query(GetEpisodesListQuery()).await()
    }

    override suspend fun getEpisodeByID(id: String): Response<GetEpisodeByIDQuery.Data> {
        return networkService.getAPIClient().query(GetEpisodeByIDQuery(id)).await()
    }

    override suspend fun getLocationsList(): Response<GetLocationsListQuery.Data> {
        return networkService.getAPIClient().query(GetLocationsListQuery()).await()
    }

    override suspend fun getLocationByID(id: String): Response<GetLocationByIDQuery.Data> {
        return networkService.getAPIClient().query(GetLocationByIDQuery(id)).await()
    }
}