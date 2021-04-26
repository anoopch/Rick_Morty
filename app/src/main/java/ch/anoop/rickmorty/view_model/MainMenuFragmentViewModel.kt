package ch.anoop.rickmorty.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ch.anoop.rickmorty.view.NavigationAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainMenuFragmentViewModel @Inject constructor() : ViewModel() {

    private val _buttonClickHandler by lazy { MutableLiveData<NavigationAction>() }
    val buttonClickHandler: LiveData<NavigationAction>
        get() = _buttonClickHandler

    fun onClickCharactersButton() {
        _buttonClickHandler.postValue(NavigationAction.CharacterOption())
    }

    fun onClickEpisodesButton() {
        _buttonClickHandler.postValue(NavigationAction.EpisodeOption())
    }

    fun onClickLocationsButton() {
        _buttonClickHandler.postValue(NavigationAction.LocationOption())
    }

    fun resetSelectionOption() {
        _buttonClickHandler.postValue(null)
    }
}