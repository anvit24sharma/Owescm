package com.owescm.client.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owescm.client.R
import com.owescm.client.model.InvitedSuppliersListModel

class InvitedSuppliersListAdapter(private var mContext: Context?, private var invitedSuppliersList: ArrayList<InvitedSuppliersListModel>) : RecyclerView.Adapter<InvitedSuppliersListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_erfx_new_invited_supplier_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(invitedSuppliersList[position], position)
    }

    override fun getItemCount(): Int {
        return invitedSuppliersList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvSName = itemView.findViewById<TextView>(R.id.tv_supplierName)
        private val tvSContactPersonName = itemView.findViewById<TextView>(R.id.tv_supplierContactPersonName)
        private var tvSMobileNo = itemView.findViewById<TextView>(R.id.tv_supplierMobileNo)
        private var tvSEmail = itemView.findViewById<TextView>(R.id.tv_supplierEmail)

        fun setData(supplierModel: InvitedSuppliersListModel, position: Int) {
            tvSName.text = supplierModel.supplierName
            tvSContactPersonName.text = supplierModel.contactPersonName
            tvSMobileNo.text = supplierModel.supplierMobNumber.toString()
            tvSEmail.text = supplierModel.supplierEmail
        }

    }
}
