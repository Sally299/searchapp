package com.nurmamedova.searchapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nurmamedova.searchapp.SearchApp
import com.nurmamedova.searchapp.data.utils.StatusResponse
import com.nurmamedova.searchapp.data.utils.ViewModelFactory
import com.nurmamedova.searchapp.databinding.FragmentListBinding
import com.nurmamedova.searchapp.ui.MoviesAdapter
import com.nurmamedova.searchapp.ui.viewmodels.MoviesViewModel
import javax.inject.Inject

class ListFragment: Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var moviesAdapter: MoviesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MoviesViewModel>
    private val viewModel by activityViewModels<MoviesViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as SearchApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater)

        moviesAdapter = MoviesAdapter(
            openMovieDescription = this::openMovieDescription
        )

        binding.rvMovies.apply {
            adapter = moviesAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.btnSearch.setOnClickListener {
//            findNavController().navigate(ListFragmentDirections.actionListFragmentToMovieDescriptionFragment2())
//        }

        viewModel.movieModelResponse.observe(viewLifecycleOwner) {
            when (it) {
                is StatusResponse.Loading -> {
                    binding.progressBar.isVisible = it.isLoading
                }

                is StatusResponse.Failure -> {
                    if (it.errorMessage == "Unable to resolve host \"kinopoiskapiunofficial.tech\": No address associated with hostname") {
                        binding.imgError.visibility = View.VISIBLE
                        binding.txtErrorUp.visibility = View.VISIBLE
                        binding.txtErrorDown.visibility = View.VISIBLE
                    } else Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_SHORT)
                        .show()
                    binding.progressBar.isVisible = false
                }

                is StatusResponse.Success -> {
                    moviesAdapter.updateMovies(it.data)
                    binding.progressBar.isVisible = false
                }
            }

        }
    }


    private fun openMovieDescription(movieId: String) {
        findNavController().navigate(
            ListFragmentDirections.actionListFragmentToMovieDescriptionFragment2(movieId)
        )
    }
}

