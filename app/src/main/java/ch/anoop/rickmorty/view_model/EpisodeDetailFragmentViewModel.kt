package ch.anoop.rickmorty.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.anoop.rickmorty.GetCharacterByIDQuery
import ch.anoop.rickmorty.GetEpisodeByIDQuery
import ch.anoop.rickmorty.repository.network.NetworkRepository
import ch.anoop.rickmorty.view.ViewState
import com.apollographql.apollo.api.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class EpisodeDetailFragmentViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
) : ViewModel() {

    private val _episode by lazy { MutableLiveData<ViewState<Response<GetEpisodeByIDQuery.Data>>>() }
    val episode: LiveData<ViewState<Response<GetEpisodeByIDQuery.Data>>>
        get() = _episode

    fun queryEpisodeById(id: String) = viewModelScope.launch {
        try {
            _episode.postValue(ViewState.Loading())
            _episode.postValue(ViewState.Success(networkRepository.getEpisodeByID(id)))
            Log.d("VIEW_MODEL", "success")
        } catch (e: Exception) {
            Log.d("VIEW_MODEL", "Failure", e)
            _episode.postValue(ViewState.Error("Error fetching episode detail for id - $id"))
        }
    }
}