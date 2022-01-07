package com.example.mypokemondata.api

import com.example.mypokemondata.database.PokemonEntity

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<PokemonEntity>
)