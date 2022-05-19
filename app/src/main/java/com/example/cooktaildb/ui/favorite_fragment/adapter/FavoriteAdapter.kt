package com.example.cooktaildb.ui.favorite_fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.databinding.ItemFavoriteBinding

class FavoriteAdapter(private val context: Context) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private val drinkList = mutableListOf<Drink>()
    private var listener: OnItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(drinkList[position])
    }

    override fun getItemCount(): Int = drinkList.size

    fun setData(drinks: List<Drink>) {
        drinkList.clear()
        drinkList.addAll(drinks)
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: OnItemClick) {
        this.listener = listener
    }

    inner class ViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(drink: Drink) {
            binding.apply {
                Glide.with(context).load(drink.strDrinkThumb).into(imageFavorite)
                textFavoriteName.text = drink.strDrink
                textFavoriteCategory.text = drink.strCategory
                textGlass.text = drink.strGlass
                root.setOnClickListener {
                    listener?.onClick(drink)
                }
            }
        }
    }

    interface OnItemClick {
        fun onClick(drink: Drink)
    }
}
