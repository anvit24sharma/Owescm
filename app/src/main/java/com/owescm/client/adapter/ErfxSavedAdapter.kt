package com.owescm.client.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owescm.client.Model.ErfxSavedModel
import com.owescm.client.R

class ErfxSavedAdapter
    (
    private var mContext: Context?,
    private var erfxSavedList: ArrayList<ErfxSavedModel>
) : RecyclerView.Adapter<ErfxSavedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_erfx_live, parent, false)
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


        fun setData(savedModel: ErfxSavedModel, position: Int) {
            tvErfxId.text = savedModel.erfxId.toString()
            tvHeadline.text=savedModel.textHeadline
            tvLocation.text = savedModel.location
            tvDate.text = savedModel.createdAtDate.toString()
            tvTime.text = savedModel.createdAtTime.toString()


        }

    }
}
