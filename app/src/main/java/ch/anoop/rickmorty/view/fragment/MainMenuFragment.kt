package ch.anoop.rickmorty.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ch.anoop.rickmorty.databinding.FragmentMainMenuBinding
import ch.anoop.rickmorty.view.NavigationAction
import ch.anoop.rickmorty.view_model.MainMenuFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainMenuFragment : Fragment() {

    private lateinit var binding: FragmentMainMenuBinding
    private val viewModel by viewModels<MainMenuFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
    }

    private fun observeLiveData() {
        binding.mainMenuFragmentViewModel = viewModel
        viewModel.buttonClickHandler.observe(viewLifecycleOwner) { response ->

            when (response) {
                is NavigationAction.CharacterOption -> {
                    findNavController().navigate(
                        MainMenuFragmentDirections.navigateToCharacterListFragment()
                    )
                    viewModel.resetSelectionOption()
                }

                is NavigationAction.EpisodeOption -> {
                    findNavController().navigate(
                        MainMenuFragmentDirections.navigateToEpisodeListFragment()
                    )
                    viewModel.resetSelectionOption()
                }

                is NavigationAction.LocationOption -> {
                    findNavController().navigate(
                        MainMenuFragmentDirections.navigateToLocationListFragment()
                    )
                    viewModel.resetSelectionOption()
                }
            }
        }
    }
}
