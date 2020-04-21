package com.owescm.supplier.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owescm.supplier.R
import com.owescm.supplier.model.EbidClosedModel

class EbidClosedAdapter
    (
    private var mContext: Context?,
    private var ebidClosedList: ArrayList<EbidClosedModel>
) : RecyclerView.Adapter<EbidClosedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ebid_closed, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(ebidClosedList[position], position)
    }

    override fun getItemCount(): Int {
        return ebidClosedList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvErfxNo = itemView.findViewById<TextView>(R.id.tv_eRFX_No)
        private val tvErfxStatus = itemView.findViewById<TextView>(R.id.tv_erfxStatus)
        private var tvLocation = itemView.findViewById<TextView>(R.id.tv_location)
        private var tvDate = itemView.findViewById<TextView>(R.id.tv_createdAt_Date)
        private var tvTime = itemView.findViewById<TextView>(R.id.tv_createdAt_Time)
        private var tvSecurity = itemView.findViewById<TextView>(R.id.tv_Security)
        private var tvTestHeadline = itemView.findViewById<TextView>(R.id.tv_test_headline)


        fun setData(closedModel: EbidClosedModel, position: Int) {
            tvErfxNo.text = closedModel.erfxNo.toString()
            tvErfxStatus.text = closedModel.erfxStatus
            tvLocation.text = closedModel.location
            tvDate.text = closedModel.createdAtDate.toString()
            tvTime.text = closedModel.createdAtTime.toString()
            tvSecurity.text = closedModel.textSecurity
            tvTestHeadline.text = closedModel.textTestHeadLine


        }

    }
}
