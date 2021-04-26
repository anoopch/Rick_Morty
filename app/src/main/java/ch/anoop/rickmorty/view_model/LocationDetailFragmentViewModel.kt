package ch.anoop.rickmorty.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.anoop.rickmorty.GetCharacterByIDQuery
import ch.anoop.rickmorty.GetLocationByIDQuery
import ch.anoop.rickmorty.repository.network.NetworkRepository
import ch.anoop.rickmorty.view.ViewState
import com.apollographql.apollo.api.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class LocationDetailFragmentViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
) : ViewModel() {

    private val _location by lazy { MutableLiveData<ViewState<Response<GetLocationByIDQuery.Data>>>() }
    val location: LiveData<ViewState<Response<GetLocationByIDQuery.Data>>>
        get() = _location

    fun queryLocationById(id: String) = viewModelScope.launch {
        try {
            _location.postValue(ViewState.Loading())
            _location.postValue(ViewState.Success(networkRepository.getLocationByID(id)))
            Log.d("VIEW_MODEL", "success")
        } catch (e: Exception) {
            Log.d("VIEW_MODEL", "Failure", e)
            _location.postValue(ViewState.Error("Error fetching Location detail for id - $id"))
        }
    }
}