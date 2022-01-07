package com.example.mypokemondata.ui.mainpage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokemondata.database.PokemonEntity
import com.example.mypokemondata.databinding.ViewItemBinding

class PokemonAdapter(private val onClickListener: OnClickListener) : ListAdapter<PokemonEntity, PokemonAdapter.PokemonPropertiesViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonAdapter.PokemonPropertiesViewHolder {
        return PokemonPropertiesViewHolder(ViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: PokemonAdapter.PokemonPropertiesViewHolder,
        position: Int
    ) {
        val resultProperty = getItem(position)
        holder.bind(resultProperty)
        holder.itemView.setOnClickListener {
            onClickListener.clickListener(resultProperty)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PokemonEntity>() {
        override fun areItemsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: PokemonEntity,
            newItem: PokemonEntity
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }

    class PokemonPropertiesViewHolder(private val binding: ViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: PokemonEntity) {
            binding.textViewName.text = result.name
            binding.textViewUrl.text = result.url
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (result: PokemonEntity) -> Unit) {
        fun onClick(result: PokemonEntity) = clickListener(result)
    }
}