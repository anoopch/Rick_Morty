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
import ch.anoop.rickmorty.databinding.FragmentListEpisodeBinding
import ch.anoop.rickmorty.view.ViewState
import ch.anoop.rickmorty.view.recyclerview.EpisodeAdapter
import ch.anoop.rickmorty.view_model.EpisodeListFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class EpisodeListFragment : Fragment() {
    private lateinit var binding: FragmentListEpisodeBinding
    private val episodeAdapter by lazy { EpisodeAdapter() }
    private val viewModel by viewModels<EpisodeListFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListEpisodeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewList.adapter = episodeAdapter
        viewModel.queryEpisodesList()
        observeLiveData()

        episodeAdapter.onItemClicked = { episode ->
            episode.let {
                if (!episode.id.isNullOrBlank()) {
                    Log.e("EPISODE_LIST_FRAG", "Clicked - : " + episode.name)
                    findNavController().navigate(
                        EpisodeListFragmentDirections.navigateToEpisodeDetailFragment(episode.id)
                    )
                }
            }
        }
    }

    private fun observeLiveData() {
        viewModel.episodesList.observe(viewLifecycleOwner) { response ->
            when (response) {

                is ViewState.Loading<*> -> {
                    binding.recyclerviewList.visibility = View.GONE
                    binding.emptyText.visibility = View.GONE
                    binding.progressBarLoading.visibility = View.VISIBLE
                }

                is ViewState.Success<*> -> {
                    if (response.value?.data?.episodes?.results?.size == 0) {
                        setErrorView(R.string.no_episodes_found)
                    } else {
                        binding.recyclerviewList.visibility = View.VISIBLE
                        episodeAdapter.submitList(response.value?.data?.episodes?.results)
                        binding.emptyText.visibility = View.GONE
                        binding.progressBarLoading.visibility = View.GONE
                    }
                }

                is ViewState.Empty<*> -> {
                    setErrorView(R.string.no_episodes_found)
                }

                is ViewState.Error<*> -> {
                    setErrorView(R.string.error_message_fail_to_load)
                }
            }
        }
    }

    private fun setErrorView(stringResourceId: Int) {
        episodeAdapter.submitList(emptyList())
        binding.recyclerviewList.visibility = View.GONE
        binding.emptyText.text = getString(stringResourceId)
        binding.emptyText.visibility = View.VISIBLE
        binding.progressBarLoading.visibility = View.GONE
    }

}