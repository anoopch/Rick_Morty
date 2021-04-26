package ch.anoop.rickmorty.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.anoop.rickmorty.GetCharacterByIDQuery
import ch.anoop.rickmorty.repository.network.NetworkDataSource
import ch.anoop.rickmorty.view.ViewState
import com.apollographql.apollo.api.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class CharacterDetailFragmentViewModel @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) : ViewModel() {

    private val _character by lazy { MutableLiveData<ViewState<Response<GetCharacterByIDQuery.Data>>>() }
    val character: LiveData<ViewState<Response<GetCharacterByIDQuery.Data>>>
        get() = _character

    fun queryCharacterById(id: String) = viewModelScope.launch {
        try {
            _character.postValue(ViewState.Loading())
            _character.postValue(ViewState.Success(networkDataSource.getCharacterByID(id)))
            Log.d("VIEW_MODEL", "success")
        } catch (e: Exception) {
            Log.d("VIEW_MODEL", "Failure", e)
            _character.postValue(ViewState.Error("Error fetching character detail for id - $id"))
        }
    }
}