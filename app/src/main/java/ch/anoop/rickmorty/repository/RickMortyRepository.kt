package ch.anoop.rickmorty.repository

import ch.anoop.rickmorty.repository.database.LocalDataSource
import ch.anoop.rickmorty.repository.network.NetworkDataSource
import javax.inject.Inject

class RickMortyRepository @Inject constructor(
    private val NetworkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
) {

}