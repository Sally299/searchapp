package com.nurmamedova.searchapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nurmamedova.searchapp.SearchApp
import com.nurmamedova.searchapp.data.utils.StatusResponse
import com.nurmamedova.searchapp.data.utils.ViewModelFactory
import com.nurmamedova.searchapp.databinding.FragmentMovieDescriptionBinding
import com.nurmamedova.searchapp.ui.viewmodels.MovieDescriptionViewModel
import javax.inject.Inject

class MovieDescriptionFragment : Fragment() {

    private lateinit var binding: FragmentMovieDescriptionBinding
    private lateinit var movieId: String

    private val args: MovieDescriptionFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MovieDescriptionViewModel>
    private lateinit var viewModel: MovieDescriptionViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as SearchApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDescriptionBinding.inflate(inflater)
        movieId = args.movieId
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = viewModelFactory.getViewModel(this)
        viewModel.fetchMovieDescription(movieId.toInt())

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.movieDescriptionResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is StatusResponse.Loading -> {
                    binding.progressBar.isVisible = response.isLoading
                }

                is StatusResponse.Failure -> {
                    if (response.errorMessage == "Unable to resolve host \"kinopoiskapiunofficial.tech\": No address associated with hostname") {
                        binding.tvMovieTitle.visibility = View.GONE
                        binding.tvMovieYearTitle.visibility = View.GONE
                        binding.imgError.visibility = View.VISIBLE
                        binding.txtErrorUp.visibility = View.VISIBLE
                        binding.txtErrorDown.visibility = View.VISIBLE
                    } else Toast.makeText(
                        requireContext(),
                        response.errorMessage,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    binding.progressBar.isVisible = false
                }

                is StatusResponse.Success -> {
                    binding.progressBar.isVisible = false

                    Glide
                        .with(binding.imgMoviePoster.context)
                        .load(response.data.posterUrl)
                        .into(binding.imgMoviePoster)

                    if (response.data.name == null) binding.tvMovieTitle.text =
                        response.data.name
                    else binding.tvMovieTitle.text = response.data.name
                    if (response.data.description != null) binding.tvMovieDescription.text =
                        response.data.description

                    binding.tvMovieYear.text = response.data.year


                }

            }
        }
    }

}