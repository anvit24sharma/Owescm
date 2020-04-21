package com.owescm.client.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owescm.client.Model.FinalEvaluationClosedModel
import com.owescm.client.R

class FinalEvaluationClosedAdapter
    (
    private var mContext: Context?,
    private var ClosedList: ArrayList<FinalEvaluationClosedModel>
) : RecyclerView.Adapter<FinalEvaluationClosedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_final_evaluation_closed, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(ClosedList[position], position)
    }

    override fun getItemCount(): Int {
        return ClosedList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvErfxNo = itemView.findViewById<TextView>(R.id.tv_eRFX_No)
        private val tvErfxStatus = itemView.findViewById<TextView>(R.id.tv_erfxStatus)
        private var tvLocation = itemView.findViewById<TextView>(R.id.tv_location)
        private var tvDate = itemView.findViewById<TextView>(R.id.tv_createdAt_Date)
        private var tvTime = itemView.findViewById<TextView>(R.id.tv_createdAt_Time)
        private var tvSecurity = itemView.findViewById<TextView>(R.id.tv_Security)
        private var tvTestHeadline = itemView.findViewById<TextView>(R.id.tv_test_headline)




        fun setData(ClosedModel: FinalEvaluationClosedModel, position: Int) {
            tvErfxNo.text = ClosedModel.erfxNo.toString()
            tvErfxStatus.text = ClosedModel.erfxStatus
            tvLocation.text = ClosedModel.location
            tvDate.text = ClosedModel.createdAtDate.toString()
            tvTime.text = ClosedModel.createdAtTime.toString()
            tvSecurity.text = ClosedModel.textSecurity
            tvTestHeadline.text = ClosedModel.textTestHeadLine



        }

    }
}
