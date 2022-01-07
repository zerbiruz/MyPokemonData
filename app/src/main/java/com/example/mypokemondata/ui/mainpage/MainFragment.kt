package com.example.mypokemondata.ui.mainpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mypokemondata.viewmodels.MainViewModel
import com.example.mypokemondata.databinding.FragmentMainBinding
import com.example.mypokemondata.viewmodels.ApiStatus
import com.example.mypokemondata.viewmodels.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application

        viewModelFactory = MainViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        binding.viewModel = viewModel

        val adapter = PokemonAdapter(PokemonAdapter.OnClickListener{
            Snackbar.make(binding.root, it.name, Snackbar.LENGTH_SHORT).show()
        })

        binding.pokemonList.adapter = adapter

        viewModel.pokemonResults.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.swipeContainer.setOnRefreshListener {
            viewModel.refreshDataFromRepository()
        }

        viewModel.status.observe(viewLifecycleOwner, Observer {
            when (it) {
                ApiStatus.LOADING -> binding.swipeContainer.isRefreshing = true
                ApiStatus.DONE -> binding.swipeContainer.isRefreshing = false
            }
        })

        // Inflate the layout for this fragment
        return binding.root
    }
}