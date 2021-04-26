package ch.anoop.rickmorty.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.anoop.rickmorty.GetEpisodesListQuery
import ch.anoop.rickmorty.repository.network.NetworkDataSource
import ch.anoop.rickmorty.view.ViewState
import com.apollographql.apollo.api.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class EpisodeListFragmentViewModel @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) : ViewModel() {

    private val _episodesList by lazy { MutableLiveData<ViewState<Response<GetEpisodesListQuery.Data>>>() }
    val episodesList: LiveData<ViewState<Response<GetEpisodesListQuery.Data>>>
        get() = _episodesList

    fun queryEpisodesList() = viewModelScope.launch {
        _episodesList.postValue(ViewState.Loading())
        try {
            _episodesList.postValue(ViewState.Success(networkDataSource.getEpisodesList()))
        } catch (e: Exception) {
            Log.d("VIEW_MODEL", "Failure", e)
            _episodesList.postValue(ViewState.Error("Error fetching episodes"))
        }
    }
}