package com.owescm.client.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owescm.client.Model.PrimaryEvaluationOpenModel
import com.owescm.client.R

class PrimaryEvaluationOpenAdapter
    (
    private var mContext: Context?,
    private var openList: ArrayList<PrimaryEvaluationOpenModel>
) : RecyclerView.Adapter<PrimaryEvaluationOpenAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_primary_evaluation_open, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(openList[position], position)
    }

    override fun getItemCount(): Int {
        return openList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvErfxId = itemView.findViewById<TextView>(R.id.tv_eRFX_id)
        private var tvClientNAme = itemView.findViewById<TextView>(R.id.tv_clientName)
        private var tvLocation = itemView.findViewById<TextView>(R.id.tv_location)



        fun setData(openModel: PrimaryEvaluationOpenModel, position: Int) {
            tvErfxId.text = openModel.erfxId.toString()
            tvClientNAme.text=openModel.clientName
            tvLocation.text = openModel.location


        }

    }
}
