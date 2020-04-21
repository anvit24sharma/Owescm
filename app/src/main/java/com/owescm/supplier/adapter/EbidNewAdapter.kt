package com.owescm.supplier.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owescm.supplier.R
import com.owescm.supplier.model.EbidNewModel

class EbidNewAdapter
    (
    private var mContext: Context?,
    private var ebidNewList: ArrayList<EbidNewModel>
) : RecyclerView.Adapter<EbidNewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ebid_new, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(ebidNewList[position], position)
    }

    override fun getItemCount(): Int {
        return ebidNewList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvErfxNo = itemView.findViewById<TextView>(R.id.tv_eRFX_No)
        private val tvErfxStatus = itemView.findViewById<TextView>(R.id.tv_erfxStatus)
        private var tvLocation = itemView.findViewById<TextView>(R.id.tv_location)
        private var tvDate = itemView.findViewById<TextView>(R.id.tv_createdAt_Date)
        private var tvTime = itemView.findViewById<TextView>(R.id.tv_createdAt_Time)
        private var tvSecurity = itemView.findViewById<TextView>(R.id.tv_Security)
        private var tvTestHeadline = itemView.findViewById<TextView>(R.id.tv_test_headline)


        fun setData(newModel: EbidNewModel, position: Int) {
            tvErfxNo.text = newModel.erfxNo.toString()
            tvErfxStatus.text = newModel.erfxStatus
            tvLocation.text = newModel.location
            tvDate.text = newModel.createdAtDate.toString()
            tvTime.text = newModel.createdAtTime.toString()
            tvSecurity.text = newModel.textSecurity
            tvTestHeadline.text = newModel.textTestHeadLine


        }

    }
}
