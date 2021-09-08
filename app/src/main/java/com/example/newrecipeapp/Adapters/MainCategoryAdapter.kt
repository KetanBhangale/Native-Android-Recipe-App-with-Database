package com.example.newrecipeapp.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newrecipeapp.databinding.CategoryRowBinding
import com.example.newrecipeapp.model.Category
import com.example.newrecipeapp.model.CategoryItem

class MainCategoryAdapter(private val listener:IMainCategoryAdapter):RecyclerView.Adapter<MainCategoryAdapter.CategoryViewHolder>(){

    class CategoryViewHolder(val binding:CategoryRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category =  categoryList[position]
       holder.binding.apply {
            title.text = category.strCategory
           desc.text = category.strCategoryDescription
           Glide.with(holder.itemView.context).load(category.strCategoryThumb).into(catImage)

           mainCategoryLayout.setOnClickListener {
               Log.i("main","Meal Adapter called=$category.strCategory")
               listener.onCategoryClick(category.strCategory)
           }
       }
    }

    override fun getItemCount(): Int = categoryList.size

    private val diffCallBack = object : DiffUtil.ItemCallback<CategoryItem>(){
        override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
            return  oldItem.idCategory == newItem.idCategory
        }

        override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
            return oldItem==newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    var categoryList: List<CategoryItem>
    get() = differ.currentList
    set(value) {differ.submitList(value)}



}

interface IMainCategoryAdapter{
    fun onCategoryClick(category: String)
}