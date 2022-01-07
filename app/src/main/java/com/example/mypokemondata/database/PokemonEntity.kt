package com.example.mypokemondata.database

import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mypokemondata.api.Result

@Entity
class PokemonEntity constructor(
    val name: String,
    @PrimaryKey
    val url: String
)

fun List<PokemonEntity>.asDomainModel(): List<Result> {
    return map {
        Result(
            name = it.name,
            url = it.url
        )
    }
}