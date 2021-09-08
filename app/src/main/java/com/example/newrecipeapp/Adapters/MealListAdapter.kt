package com.example.newrecipeapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newrecipeapp.databinding.CategoryRowBigViewBinding
import com.example.newrecipeapp.model.MealByCategoryItem

class MealListAdapter(private val listener:IMealAdapter):RecyclerView.Adapter<MealListAdapter.MealListViewHolder>() {

    class MealListViewHolder(val binding: CategoryRowBigViewBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealListViewHolder {
        return MealListViewHolder(CategoryRowBigViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MealListViewHolder, position: Int) {
        val meal = mealList[position]
        holder.binding.apply {
            catTitle.text = meal.strMeal
            Glide.with(holder.itemView.context).load(meal.strMealThumb).into(catImage)
            mealLayout.setOnClickListener {
                listener.onMealItemClicked(meal.idMeal)
            }
        }
    }

    override fun getItemCount() = mealList.size

    private val diffCallBack = object : DiffUtil.ItemCallback<MealByCategoryItem>(){
        override fun areItemsTheSame(oldItem: MealByCategoryItem, newItem: MealByCategoryItem): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: MealByCategoryItem, newItem: MealByCategoryItem): Boolean {
            return  oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    var mealList: List<MealByCategoryItem>
    get() = differ.currentList
    set(value) {differ.submitList(value)}

}

interface IMealAdapter{
    fun onMealItemClicked(mealId:String)
}