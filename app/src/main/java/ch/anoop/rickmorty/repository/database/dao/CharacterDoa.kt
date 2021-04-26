package ch.anoop.rickmorty.repository.database.dao

abstract class CharacterDoa {
    abstract fun getCharactersList(pageNumber: Int): List<Any>
    abstract fun getCharacterByID(id: String): Any

}
