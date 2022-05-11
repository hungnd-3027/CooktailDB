package com.example.cooktaildb.ui.home_fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cooktaildb.data.model.Category
import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.databinding.ItemCategoryBinding

class DrinkViewPagerAdapter(val context: Context) :
    RecyclerView.Adapter<DrinkViewPagerAdapter.EventViewHolder>() {
    private val listCategory = mutableListOf<Category>()
    private var drinkAdapter = DrinkAdapter(context)

    fun setData(listCategory: List<Category>) {
        this.listCategory.apply {
            clear()
            addAll(listCategory)
        }
        notifyDataSetChanged()
    }

    fun setDrinkData(listDrink: MutableList<Drink>) {
        this.drinkAdapter.setData(listDrink)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(listCategory[position])
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }

    inner class EventViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.apply {
                recyclerDrink.adapter = drinkAdapter
            }
        }
    }
}
