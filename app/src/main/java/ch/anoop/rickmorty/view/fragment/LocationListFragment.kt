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
import ch.anoop.rickmorty.databinding.FragmentListCharacterBinding
import ch.anoop.rickmorty.view.ViewState
import ch.anoop.rickmorty.view.recyclerview.CharacterAdapter
import ch.anoop.rickmorty.view_model.CharacterListFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LocationListFragment : Fragment() {
    private lateinit var binding: FragmentListCharacterBinding
    private val characterAdapter by lazy { CharacterAdapter() }
    private val viewModel by viewModels<CharacterListFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListCharacterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewList.adapter = characterAdapter
        viewModel.queryCharactersList()
        observeLiveData()

        characterAdapter.onItemClicked = { character ->
            character.let {
                if (!character.id.isNullOrBlank()) {
                    Log.e("CHAR_LIST_FRAG", "Clicked - : " + character.name)
                    findNavController().navigate(
                        CharacterListFragmentDirections.navigateToCharacterDetailsFragment(character.id)
                    )
                }
            }
        }
    }

    private fun observeLiveData() {
        viewModel.charactersList.observe(viewLifecycleOwner) { response ->
            when (response) {

                is ViewState.Loading<*> -> {
                    binding.recyclerviewList.visibility = View.GONE
                    binding.emptyText.visibility = View.GONE
                    binding.progressBarLoading.visibility = View.VISIBLE
                }

                is ViewState.Success<*> -> {
                    if (response.value?.data?.characters?.results?.size == 0) {
                        setErrorView(R.string.no_characters_found)
                    } else {
                        binding.recyclerviewList.visibility = View.VISIBLE
                        characterAdapter.submitList(response.value?.data?.characters?.results)
                        binding.emptyText.visibility = View.GONE
                        binding.progressBarLoading.visibility = View.GONE
                    }
                }

                is ViewState.Empty<*> -> {
                    setErrorView(R.string.no_characters_found)
                }

                is ViewState.Error<*> -> {
                    setErrorView(R.string.error_message_fail_to_load)
                }
            }
        }
    }

    private fun setErrorView(stringResourceId: Int) {
        characterAdapter.submitList(emptyList())
        binding.recyclerviewList.visibility = View.GONE
        binding.emptyText.text = getString(stringResourceId)
        binding.emptyText.visibility = View.VISIBLE
        binding.progressBarLoading.visibility = View.GONE
    }

}