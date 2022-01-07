package com.example.mypokemondata.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.mypokemondata.database.PokemonDatabase
import com.example.mypokemondata.repository.PokemonRepository
import retrofit2.HttpException
import timber.log.Timber

class RefreshDataWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "com.example.mypokemondata.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = PokemonDatabase.getDatabase(applicationContext)
        val repository = PokemonRepository(database)

        try {
            repository.refreshPokemon()
            Timber.d("WorkManager: Work request for sync is run")
        } catch (e: HttpException) {
            Result.retry()
        }

        return Result.success()
    }
}