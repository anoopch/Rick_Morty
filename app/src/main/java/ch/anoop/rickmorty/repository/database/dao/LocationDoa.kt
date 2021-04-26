package ch.anoop.rickmorty.repository.database.dao

abstract class LocationDoa {

    abstract fun getLocationsList(pageNumber: Int): List<Any>
    abstract fun getLocationByID(id: String): Any

}
