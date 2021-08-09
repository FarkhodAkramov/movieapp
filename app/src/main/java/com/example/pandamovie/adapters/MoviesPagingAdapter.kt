package com.example.pandamovie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pandamovie.R
import com.example.pandamovie.databinding.ItemMovieBinding
import com.example.pandamovie.models.Result
import com.example.pandamovie.utils.loadImage
import com.squareup.picasso.Picasso

class MoviesPagingAdapter(val listener:OnClickListener) :
    PagingDataAdapter<com.example.pandamovie.models.Result, MoviesPagingAdapter.Vh>(MyDiffUtill()) {



    class MyDiffUtill : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

    inner class Vh(var itemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {
        fun onBind(result: Result) {
            Picasso.get()
                .load("https://image.tmdb.org/t/p/original${result.poster_path}")
                .into(itemMovieBinding.imageview)
            itemMovieBinding.nameTv.text = result.title
            itemMovieBinding.root.setOnClickListener {
                listener.OnItemClick(result)
            }
        }

    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    interface OnClickListener{
        fun OnItemClick(result: Result)
    }

}