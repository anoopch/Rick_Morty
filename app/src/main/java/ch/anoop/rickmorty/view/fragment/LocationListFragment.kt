package ch.anoop.rickmorty.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ch.anoop.rickmorty.R
import ch.anoop.rickmorty.databinding.FragmentListLocationBinding
import ch.anoop.rickmorty.view.ViewState
import ch.anoop.rickmorty.view.recyclerview.LocationAdapter
import ch.anoop.rickmorty.view_model.LocationListFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LocationListFragment : Fragment() {
    private lateinit var binding: FragmentListLocationBinding
    private val locationAdapter by lazy { LocationAdapter() }
    private val viewModel by viewModels<LocationListFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListLocationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewList.adapter = locationAdapter
        viewModel.queryLocationList()
        observeLiveData()

        locationAdapter.onItemClicked = { location ->
            location.let {
                if (!location.id.isNullOrBlank()) {
                    Log.e("CHAR_LIST_FRAG", "Clicked - : " + location.name)
                    findNavController().navigate(
                        LocationListFragmentDirections.navigateToLocationDetailFragment(location.id)
                    )
                }
            }
        }
    }

    private fun observeLiveData() {
        viewModel.locationList.observe(viewLifecycleOwner) { response ->
            when (response) {

                is ViewState.Loading<*> -> {
                    binding.recyclerviewList.visibility = View.GONE
                    binding.emptyText.visibility = View.GONE
                    binding.progressBarLoading.visibility = View.VISIBLE
                }

                is ViewState.Success<*> -> {
                    if (response.value?.data?.locations?.results?.size == 0) {
                        setErrorView(R.string.no_locations_found)
                    } else {
                        binding.recyclerviewList.visibility = View.VISIBLE
                        locationAdapter.submitList(response.value?.data?.locations?.results)
                        binding.emptyText.visibility = View.GONE
                        binding.progressBarLoading.visibility = View.GONE
                    }
                }

                is ViewState.Empty<*> -> {
                    setErrorView(R.string.no_locations_found)
                }

                is ViewState.Error<*> -> {
                    setErrorView(R.string.error_message_fail_to_load)
                }
            }
        }
    }

    private fun setErrorView(stringResourceId: Int) {
        locationAdapter.submitList(emptyList())
        binding.recyclerviewList.visibility = View.GONE
        binding.emptyText.text = getString(stringResourceId)
        binding.emptyText.visibility = View.VISIBLE
        binding.progressBarLoading.visibility = View.GONE
    }

}