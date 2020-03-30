package com.owescm.client

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeVerticalAdapter(
    private var mContext: Context?,
    private var homeList: ArrayList<HomeModel>
) : RecyclerView.Adapter<HomeVerticalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_home_fragment, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(homeList[position],position)
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvMenuTitle= itemView.findViewById<TextView>(R.id.menuTitle)
        private val rvItemDetails = itemView.findViewById<RecyclerView>(R.id.rv_itemDetails)

        private lateinit var homeHorizontalAdapter: HomeHorizontalAdapter

        fun setData(homeModel:HomeModel, position: Int) {
            tvMenuTitle.text = homeModel.menuTitle

            homeHorizontalAdapter = homeModel.let {
                HomeHorizontalAdapter(mContext, it.itemList ,object : HomeHorizontalAdapter.ClickListener{
                    override fun onItemClick(position: Int) {

                    }

                })
            }
            rvItemDetails.apply {
                adapter = homeHorizontalAdapter
                layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
            }

        }
    }


}



