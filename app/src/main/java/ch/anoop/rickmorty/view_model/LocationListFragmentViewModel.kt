package ch.anoop.rickmorty.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.anoop.rickmorty.GetLocationsListQuery
import ch.anoop.rickmorty.repository.network.NetworkDataSource
import ch.anoop.rickmorty.view.ViewState
import com.apollographql.apollo.api.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class LocationListFragmentViewModel @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) : ViewModel() {

    private val _locationList by lazy { MutableLiveData<ViewState<Response<GetLocationsListQuery.Data>>>() }
    val locationList: LiveData<ViewState<Response<GetLocationsListQuery.Data>>>
        get() = _locationList

    fun queryLocationList() = viewModelScope.launch {
        try {
            _locationList.postValue(ViewState.Loading())
            _locationList.postValue(ViewState.Success(networkDataSource.getLocationsList()))
            Log.d("VIEW_MODEL", "success")
        } catch (e: Exception) {
            Log.d("VIEW_MODEL", "Failure", e)
            _locationList.postValue(ViewState.Error("Error fetching Location list"))
        }
    }
}