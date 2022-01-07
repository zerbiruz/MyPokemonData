package com.example.mypokemondata.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.mypokemondata.database.PokemonDatabase
import com.example.mypokemondata.database.PokemonEntity
import com.example.mypokemondata.repository.PokemonRepository
import kotlinx.coroutines.launch
import java.io.IOException


enum class ApiStatus {
    LOADING, ERROR, DONE
}

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val pokemonRepository = PokemonRepository(PokemonDatabase.getDatabase(application))

    var pokemonResults: LiveData<List<PokemonEntity>> = pokemonRepository.pokemons

    init {
        refreshDataFromRepository()
    }

    fun refreshDataFromRepository() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                pokemonRepository.refreshPokemon()
                _status.value = ApiStatus.DONE
            } catch (networkError: IOException) {
                _status.value = ApiStatus.ERROR
            }
        }
    }
}