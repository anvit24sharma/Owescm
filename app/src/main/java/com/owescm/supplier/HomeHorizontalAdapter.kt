package com.owescm.supplier


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeHorizontalAdapter(private val context: Context?, private val products: ArrayList<ItemDetails>) : RecyclerView.Adapter<HomeHorizontalAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item__item_home_fragment, parent, false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.itemName.text = products[position].itemName
        holder.count.text = products[position].count.toString()

    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val  itemName = itemView.findViewById<View>(R.id.tv_itemName) as TextView
        val  count = itemView.findViewById<View>(R.id.tv_count) as TextView

    }


}