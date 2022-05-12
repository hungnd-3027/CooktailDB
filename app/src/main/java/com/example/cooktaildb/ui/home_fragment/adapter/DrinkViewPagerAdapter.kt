package com.example.cooktaildb.ui.home_fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cooktaildb.data.model.Category
import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.databinding.ItemCategoryBinding

class DrinkViewPagerAdapter(val context: Context) :
    RecyclerView.Adapter<DrinkViewPagerAdapter.EventViewHolder>(),
    DrinkAdapter.OnItemClickListener {

    private var listener: OnItemClickListener? = null
    private val listCategory = mutableListOf<Category>()
    private var drinkAdapter = DrinkAdapter(context)

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

    override fun onItemClick(idDrink: String) {
        listener?.onItemClick(idDrink)
    }

    fun setData(listCategory: List<Category>) {
        this.listCategory.apply {
            clear()
            addAll(listCategory)
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun setDrinkData(listDrink: MutableList<Drink>) {
        this.drinkAdapter.setData(listDrink)
        drinkAdapter.setOnItemClickListener(this)
    }

    inner class EventViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.apply {
                recyclerDrink.adapter = drinkAdapter
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(idDrink: String)
    }
}
