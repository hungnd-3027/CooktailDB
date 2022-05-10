package com.example.cooktaildb.ui.home_fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cooktaildb.R
import com.example.cooktaildb.data.model.Category
import com.example.cooktaildb.databinding.ItemCategoryBinding

class DrinkViewPagerAdapter : RecyclerView.Adapter<DrinkViewPagerAdapter.EventViewHolder>() {
    private val listCategory = mutableListOf<Category>()

    fun setData(listCategory: MutableList<Category>) {
        this.listCategory.apply {
            clear()
            addAll(listCategory)
        }
        notifyDataSetChanged()
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

    class EventViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.apply {
                textTest.text = category.strCategory
            }
        }
    }
}
