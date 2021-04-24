package ch.anoop.rickmorty.repository.network

import ch.anoop.rickmorty.GetCharacterByIDQuery
import ch.anoop.rickmorty.GetCharactersListQuery
import com.apollographql.apollo.api.Response

interface NetworkRepository {
    suspend fun getCharactersList(): Response<GetCharactersListQuery.Data>
    suspend fun getCharacterByID(id: String): Response<GetCharacterByIDQuery.Data>
}