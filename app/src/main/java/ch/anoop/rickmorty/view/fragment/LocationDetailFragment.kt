package ch.anoop.rickmorty.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ch.anoop.rickmorty.databinding.FragmentLocationDetailBinding
import ch.anoop.rickmorty.view.ViewState
import ch.anoop.rickmorty.view_model.LocationDetailFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LocationDetailFragment : Fragment() {

    private lateinit var binding: FragmentLocationDetailBinding
    private val viewModel by viewModels<LocationDetailFragmentViewModel>()
    private val fragmentArguments: LocationDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationDetailBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.queryLocationById(fragmentArguments.id)
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.location.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    binding.progressBarLoading.visibility = View.VISIBLE
                    binding.detailsContainer.visibility = View.GONE
                }

                is ViewState.Success -> {
                    if (response.value?.data?.location == null) {
                        findNavController().popBackStack()
                    } else {
                        binding.responseData = response.value.data
                        binding.progressBarLoading.visibility = View.GONE
                        binding.detailsContainer.visibility = View.VISIBLE
                    }
                }

                is ViewState.Empty -> {
                    findNavController().popBackStack()
                }

                is ViewState.Error -> {
                    findNavController().popBackStack()
                }
            }
        }
    }

}