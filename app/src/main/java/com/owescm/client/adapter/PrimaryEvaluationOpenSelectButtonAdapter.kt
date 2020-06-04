package com.owescm.client.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owescm.client.Model.PrimaryEvaluationOpenSelectBtnModel
import com.owescm.client.R

class PrimaryEvaluationOpenSelectButtonAdapter
    (
    private var mContext: Context?,
    private var openList: ArrayList<PrimaryEvaluationOpenSelectBtnModel>
) : RecyclerView.Adapter<PrimaryEvaluationOpenSelectButtonAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_primary_evaluation_open_select_btn, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(openList[position], position)
    }

    override fun getItemCount(): Int {
        return openList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvErfxNo = itemView.findViewById<TextView>(R.id.tv_eRFX_No)
        private val tvClientName = itemView.findViewById<TextView>(R.id.tv_clientName)
        private var tvLocation = itemView.findViewById<TextView>(R.id.tv_location)



        fun setData(liveModel: PrimaryEvaluationOpenSelectBtnModel, position: Int) {
            tvErfxNo.text = liveModel.erfxNo.toString()
            tvClientName.text = liveModel.clientName
            tvLocation.text = liveModel.location


        }

    }
}
