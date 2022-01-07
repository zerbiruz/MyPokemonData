package com.example.mypokemondata.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://pokeapi.co/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PokemonService {
    @GET("api/v2/pokemon")
    suspend fun getPokemon(
        @Query("limit") limit: Int = 200,
        @Query("offset") offset: Int = 0
    ): PokemonResponse?
}

object PokemonApi {
    val retrofitService: PokemonService by lazy {
        retrofit.create(PokemonService::class.java)
    }
}