package com.owescm.client.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owescm.client.R
import com.owescm.client.model.PrimaryEvaluationDetailsResponse

class PrimaryEvaluationSuppliersAdapter(
    private var mContext: Context?,
    private var suppliersList: List<PrimaryEvaluationDetailsResponse.Data.ErfxReply>,
    val mClickListener: ClickListener
) : RecyclerView.Adapter<PrimaryEvaluationSuppliersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_primary_evaluation_suppliers, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(suppliersList[position], position)
    }

    override fun getItemCount(): Int {
        return suppliersList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvSupplierName = itemView.findViewById<TextView>(R.id.tvSupplierName)
        private val tvSupplierLocation = itemView.findViewById<TextView>(R.id.tvSupplierLocation)
        private var tvValidTill = itemView.findViewById<TextView>(R.id.tvValidTill)
        private val tvOwescmScore = itemView.findViewById<TextView>(R.id.tvOwescmScore)
        private val tvQuotes = itemView.findViewById<TextView>(R.id.tvQuotes)
        private var tvRemarks = itemView.findViewById<TextView>(R.id.tvRemarks)
        private var tvtechnical = itemView.findViewById<TextView>(R.id.tvtechnical)
        private var tvCommercial = itemView.findViewById<TextView>(R.id.tvCommercial)
        private var tvRecommendation = itemView.findViewById<TextView>(R.id.tvRecommendation)
        private var imgDownload = itemView.findViewById<ImageView>(R.id.imgDownload)
        private var checkBoxShortlist = itemView.findViewById<CheckBox>(R.id.checkBoxShortlist)

        fun setData(liveModel: PrimaryEvaluationDetailsResponse.Data.ErfxReply, position: Int) {
            tvSupplierName.text = liveModel.name
            tvSupplierLocation.text = liveModel.location
            tvValidTill.text = liveModel.validTill
            tvOwescmScore.text = liveModel.score.toString()
            tvQuotes.text = liveModel.quote
            tvRemarks.text = liveModel.remarks
            tvtechnical.text = liveModel.recommendationRanking.toString()
            tvCommercial.text = liveModel.commercialRanking.toString()
            tvRecommendation.text = liveModel.finalRecommendationRanking.toString()

        }

    }
    interface ClickListener{
        fun onShortlistClick()
    }
}
