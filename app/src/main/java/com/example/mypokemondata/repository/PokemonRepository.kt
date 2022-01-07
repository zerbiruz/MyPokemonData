package com.example.mypokemondata.repository

import androidx.lifecycle.LiveData
import com.example.mypokemondata.api.PokemonApi
import com.example.mypokemondata.database.PokemonDatabase
import com.example.mypokemondata.database.PokemonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepository(private val database: PokemonDatabase) {
    val pokemons: LiveData<List<PokemonEntity>> = database.pokemonDao.getPokemon()

    suspend fun refreshPokemon() {
        withContext(Dispatchers.IO) {
            val pokemonList = PokemonApi.retrofitService.getPokemon()?.results
            if (pokemonList != null) {
                database.pokemonDao.insertAll(pokemonList)
            }
        }
    }
}