package ch.anoop.rickmorty.repository.database

interface LocalDataSource {

    suspend fun getCharactersList(pageNumber: Int): List<Any>
    suspend fun getCharacterByID(id: String): Any

    suspend fun getEpisodesList(pageNumber: Int): List<Any>
    suspend fun getEpisodeByID(id: String): Any

    suspend fun getLocationsList(pageNumber: Int): List<Any>
    suspend fun getLocationByID(id: String): Any
}