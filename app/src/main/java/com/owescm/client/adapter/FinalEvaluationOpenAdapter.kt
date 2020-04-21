package com.owescm.client.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owescm.client.Model.FinalEvaluationOpenModel
import com.owescm.client.R

class FinalEvaluationOpenAdapter
    (
    private var mContext: Context?,
    private var openList: ArrayList<FinalEvaluationOpenModel>
) : RecyclerView.Adapter<FinalEvaluationOpenAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_final_evaluation_open, parent, false)
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
        private val tvErfxStatus = itemView.findViewById<TextView>(R.id.tv_erfxStatus)
        private var tvLocation = itemView.findViewById<TextView>(R.id.tv_location)
        private var tvDate = itemView.findViewById<TextView>(R.id.tv_createdAt_Date)
        private var tvTime = itemView.findViewById<TextView>(R.id.tv_createdAt_Time)
        private var tvSecurity = itemView.findViewById<TextView>(R.id.tv_Security)
        private var tvTestHeadline = itemView.findViewById<TextView>(R.id.tv_test_headline)




        fun setData(openModel: FinalEvaluationOpenModel, position: Int) {
            tvErfxNo.text = openModel.erfxNo.toString()
            tvErfxStatus.text = openModel.erfxStatus
            tvLocation.text = openModel.location
            tvDate.text = openModel.createdAtDate.toString()
            tvTime.text = openModel.createdAtTime.toString()
            tvSecurity.text = openModel.textSecurity
            tvTestHeadline.text = openModel.textTestHeadLine



        }

    }
}
