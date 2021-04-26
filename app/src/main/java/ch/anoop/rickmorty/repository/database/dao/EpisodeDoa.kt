package ch.anoop.rickmorty.repository.database.dao

abstract class EpisodeDoa {

    abstract fun getEpisodesList(pageNumber: Int): List<Any>
    abstract fun getEpisodeByID(id: String): Any

}