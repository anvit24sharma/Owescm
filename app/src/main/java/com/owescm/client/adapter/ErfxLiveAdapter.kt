package com.owescm.client.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owescm.client.R
import com.owescm.client.model.ErfxLiveListResponse

class ErfxLiveAdapter
(
        private var mContext: Context?,
        private var erfxLiveList: ArrayList<ErfxLiveListResponse.Data>
) : RecyclerView.Adapter<ErfxLiveAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_erfx_live, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(erfxLiveList[position], position)
    }

    override fun getItemCount(): Int {
        return erfxLiveList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvErfxNo = itemView.findViewById<TextView>(R.id.tv_eRFX_No)
        private val tvErfxStatus = itemView.findViewById<TextView>(R.id.tv_erfxStatus)
        private var tvLocation = itemView.findViewById<TextView>(R.id.tv_location)
        private var tvDate = itemView.findViewById<TextView>(R.id.tv_createdAt_Date)
        private var tvTime = itemView.findViewById<TextView>(R.id.tv_createdAt_Time)
        private var tvSecurity = itemView.findViewById<TextView>(R.id.tv_Security)

        fun setData(liveModel: ErfxLiveListResponse.Data, position: Int) {
            tvErfxNo.text = liveModel.erfxId
            tvErfxStatus.text = liveModel.erfxStatus
            tvLocation.text = liveModel.location
            tvDate.text = liveModel.createdAt
            tvTime.text = liveModel.createdAt
            tvSecurity.text = liveModel.serviceSubCategoryName


        }

    }
}
