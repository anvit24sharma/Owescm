package com.owescm.client


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class HomeHorizontalAdapter(
    private val context: Context?,
    private val products: ArrayList<ItemDetails>,
    private val mListener: ClickListener

) : RecyclerView.Adapter<HomeHorizontalAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item__item_home_fragment, parent, false)
//        val height = parent.measuredHeight
//        val width = parent.measuredWidth / 4
//        itemView.setLayoutParams(RecyclerView.LayoutParams(width, height))

        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.setData(position)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(position: Int) {
            itemName.text = products[position].itemName

            if (products[position].count.toString() == ("-1"))
                count.text = "+"
            else
                count.text = products[position].count.toString()

            rlItem.setOnClickListener {
                mListener.onItemClick(position, it)
            }
        }

        val itemName = itemView.findViewById<View>(R.id.tv_itemName) as TextView
        val count = itemView.findViewById<View>(R.id.tv_count) as TextView
        val rlItem = itemView.findViewById<RelativeLayout>(R.id.rl_item) as RelativeLayout
    }

    interface ClickListener {
        fun onItemClick(position: Int, view: View)
    }


}