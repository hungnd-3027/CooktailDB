package com.example.cooktaildb.ui.home_fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cooktaildb.R
import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.databinding.ItemDrinkBinding

class DrinkAdapter(val context: Context) : RecyclerView.Adapter<DrinkAdapter.ViewHolder>() {

    private val drinkList = mutableListOf<Drink>()
    var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDrinkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(drinkList[position])
    }

    override fun getItemCount(): Int = drinkList.size

    fun setData(drinkList: List<Drink>) {
        this.drinkList.apply {
            clear()
            addAll(drinkList)
            notifyDataSetChanged()
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(private val binding: ItemDrinkBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(drink: Drink) {
            binding.apply {
                Glide.with(context).load(drink.strDrinkThumb).placeholder(R.drawable.bg_no_internet).into(imageDrink)
                textDrink.text = drink.strDrink
                root.setOnClickListener {
                    drink.idDrink?.let { idDrink -> listener?.onItemClick(idDrink) }
                }
                buttonFavorite.apply {
                    setImageResource(if (drink.isFavorite) R.drawable.ic_star_yellow else R.drawable.ic_star_detail)
                    setOnClickListener {
                        drink.idDrink?.let { idDrink ->
                            listener?.onFavoriteClick(idDrink, drink.isFavorite, adapterPosition)
                        }
                        if (drink.isFavorite) {
                            drink.isFavorite = false
                            setImageResource(R.drawable.ic_star_detail)
                        } else {
                            drink.isFavorite = true
                            setImageResource(R.drawable.ic_star_yellow)
                        }
                    }
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(idDrink: String)
        fun onFavoriteClick(idDrink: String, isFavorite: Boolean, position: Int)
    }
}
