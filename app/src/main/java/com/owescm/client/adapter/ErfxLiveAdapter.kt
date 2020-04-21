package com.owescm.client.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owescm.client.Model.ErfxLiveModel
import com.owescm.client.R

class ErfxLiveAdapter
    (
    private var mContext: Context?,
    private var erfxLiveList: ArrayList<ErfxLiveModel>
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


        fun setData(liveModel: ErfxLiveModel, position: Int) {
            tvErfxNo.text = liveModel.erfxNo.toString()
            tvErfxStatus.text = liveModel.erfxStatus
            tvLocation.text = liveModel.location
            tvDate.text = liveModel.createdAtDate.toString()
            tvTime.text = liveModel.createdAtTime.toString()
            tvSecurity.text = liveModel.textSecurity


        }

    }
}
