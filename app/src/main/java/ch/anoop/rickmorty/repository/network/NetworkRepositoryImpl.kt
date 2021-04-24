package ch.anoop.rickmorty.repository.network

import ch.anoop.rickmorty.GetCharacterByIDQuery
import ch.anoop.rickmorty.GetCharactersListQuery
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val networkService: RickMortyAPI
)  : NetworkRepository {
    override suspend fun getCharactersList(): Response<GetCharactersListQuery.Data> {
        return networkService.getAPIClient().query(GetCharactersListQuery()).await()
    }

    override suspend fun getCharacterByID(id: String): Response<GetCharacterByIDQuery.Data> {
        return networkService.getAPIClient().query(GetCharacterByIDQuery(id)).await()
    }
}