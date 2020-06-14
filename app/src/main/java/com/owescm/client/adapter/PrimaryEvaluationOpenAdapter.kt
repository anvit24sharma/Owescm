package com.owescm.client.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owescm.client.R
import com.owescm.client.model.OpenPrimaryEvaluationListResponse

class PrimaryEvaluationOpenAdapter(
        private var mContext: Context?,
        private var peOpenList: ArrayList<OpenPrimaryEvaluationListResponse.Data>,
        val mCLickListener: ClickListener
) : RecyclerView.Adapter<PrimaryEvaluationOpenAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_primary_evaluation_open, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(peOpenList[position], position)
    }

    override fun getItemCount(): Int {
        return peOpenList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvErfxId = itemView.findViewById<TextView>(R.id.tv_eRFX_id)
        private var tvClientNAme = itemView.findViewById<TextView>(R.id.tv_clientName)
        private var tvLocation = itemView.findViewById<TextView>(R.id.tv_location)
        private var btnSelect = itemView.findViewById<TextView>(R.id.btn_select)

        fun setData(openModel: OpenPrimaryEvaluationListResponse.Data, position: Int) {
            tvErfxId.text = openModel.erfxId
            tvClientNAme.text = openModel.contractFrom
            tvLocation.text = openModel.location

            btnSelect.setOnClickListener {
                mCLickListener.onSelectClick(openModel.erfxId)
            }
        }

    }

    interface ClickListener {
        fun onSelectClick(erfxId: String)
    }
}
