package com.owescm.supplier.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owescm.supplier.R
import com.owescm.supplier.model.EbidLiveModel

class EbidLiveAdapter
    (
    private var mContext: Context?,
    private var ebidLiveList: ArrayList<EbidLiveModel>
) : RecyclerView.Adapter<EbidLiveAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ebid_live, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(ebidLiveList[position], position)
    }

    override fun getItemCount(): Int {
        return ebidLiveList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvErfxNo = itemView.findViewById<TextView>(R.id.tv_eRFX_No)
        private val tvErfxStatus = itemView.findViewById<TextView>(R.id.tv_erfxStatus)
        private var tvLocation = itemView.findViewById<TextView>(R.id.tv_location)
        private var tvDate = itemView.findViewById<TextView>(R.id.tv_createdAt_Date)
        private var tvTime = itemView.findViewById<TextView>(R.id.tv_createdAt_Time)
        private var tvSecurity = itemView.findViewById<TextView>(R.id.tv_Security)
        private var tvTestHeadline = itemView.findViewById<TextView>(R.id.tv_test_headline)


        fun setData(liveModel: EbidLiveModel, position: Int) {
            tvErfxNo.text = liveModel.erfxNo.toString()
            tvErfxStatus.text = liveModel.erfxStatus
            tvLocation.text = liveModel.location
            tvDate.text = liveModel.createdAtDate.toString()
            tvTime.text = liveModel.createdAtTime.toString()
            tvSecurity.text = liveModel.textSecurity
            tvTestHeadline.text = liveModel.textTestHeadLine


        }

    }
}
