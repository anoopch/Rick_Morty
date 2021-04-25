package ch.anoop.rickmorty.view

sealed class NavigationAction(
    val value: Int? = null
) {
    class LocationOption : NavigationAction()
    class CharacterOption : NavigationAction()
    class EpisodeOption : NavigationAction()
}
