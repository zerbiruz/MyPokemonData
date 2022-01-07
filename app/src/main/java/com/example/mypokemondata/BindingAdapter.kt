package com.example.mypokemondata

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.mypokemondata.viewmodels.ApiStatus

@BindingAdapter("pokemonApiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}