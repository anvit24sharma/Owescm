package com.owescm.client.fragment.erfx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.owescm.client.R
import com.owescm.client.model.InvitedSuppliersListModel
import kotlinx.android.synthetic.main.fragment_erfx_new_invite_suppliers_b_s.*

open class ErfxNewInviteSuppliersBSFragment(
    private val from: String,
    private val mListener: BottomSheetListner
) : BottomSheetDialogFragment() {
    var bottomSheetListner: BottomSheetListner? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =
            inflater.inflate(R.layout.fragment_erfx_new_invite_suppliers_b_s, container, false)
        bottomSheetListner = mListener
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_add.setOnClickListener {
            addDetails()
        }
    }

    private fun addDetails() {

        val supplierName: String = supplierName.text.toString()
        val contactPersonName: String = contactPersonName.text.toString()
        val supplierMobileNo: String = supplierMobileNo.text.toString()
        val supplierEmail: String = supplierEmail.text.toString()

        val invitedSuppliersListModel = InvitedSuppliersListModel(supplierName, contactPersonName, supplierMobileNo, supplierEmail)
        bottomSheetListner?.onSaveClicked(invitedSuppliersListModel)
    }

    interface BottomSheetListner {
        fun onSaveClicked(string: InvitedSuppliersListModel)
    }


}
