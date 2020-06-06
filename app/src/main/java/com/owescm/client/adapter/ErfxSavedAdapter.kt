package com.owescm.client.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owescm.client.R
import com.owescm.client.model.ErfxSavedListResponse


class ErfxSavedAdapter
    (
    private var mContext: Context?,
    private var erfxSavedList: ArrayList<ErfxSavedListResponse.Data>
) : RecyclerView.Adapter<ErfxSavedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_erfx_saved, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(erfxSavedList[position], position)
    }

    override fun getItemCount(): Int {
        return erfxSavedList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvErfxId = itemView.findViewById<TextView>(R.id.tv_eRFX_id)
        private var tvHeadline = itemView.findViewById<TextView>(R.id.txt_headLine)
        private var tvLocation = itemView.findViewById<TextView>(R.id.txt_location)
        private var tvDate = itemView.findViewById<TextView>(R.id.tv_createdAt_Date)
        private var tvTime = itemView.findViewById<TextView>(R.id.tv_createdAt_Time)


        fun setData(savedModel: ErfxSavedListResponse.Data, position: Int) {
            tvErfxId.text = savedModel.erfxId
            tvHeadline.text=savedModel.headline
            tvLocation.text = savedModel.location
            tvDate.text = savedModel.createdAt
            tvTime.text = savedModel.updatedAt


        }

    }
}
