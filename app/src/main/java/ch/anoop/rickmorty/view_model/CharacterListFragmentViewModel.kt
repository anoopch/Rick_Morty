package ch.anoop.rickmorty.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.anoop.rickmorty.GetCharactersListQuery
import ch.anoop.rickmorty.repository.network.NetworkDataSource
import ch.anoop.rickmorty.view.ViewState
import com.apollographql.apollo.api.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class CharacterListFragmentViewModel @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) : ViewModel() {

    private val _charactersList by lazy { MutableLiveData<ViewState<Response<GetCharactersListQuery.Data>>>() }
    val charactersList: LiveData<ViewState<Response<GetCharactersListQuery.Data>>>
        get() = _charactersList

    var currentPageNumber=1
    fun queryCharactersList() = viewModelScope.launch {
        _charactersList.postValue(ViewState.Loading())
        try {
            _charactersList.postValue(ViewState.Success(networkDataSource.getCharactersList(currentPageNumber)))
        } catch (e: Exception) {
            Log.d("VIEW_MODEL", "Failure", e)
            _charactersList.postValue(ViewState.Error("Error fetching characters"))
        }
    }
}