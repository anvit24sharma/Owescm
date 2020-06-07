package com.owescm.client.fragment.ErfxFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.owescm.client.R
import kotlinx.android.synthetic.main.fragment_erfx_new_invite_suppliers_b_s.*

open class ErfxNewInviteSuppliersBSFragment(private val from: String, private val erfxnewfragment: ErfxNewFragment) : BottomSheetDialogFragment() {
    var bottomSheetListner: BottomSheetListner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_erfx_new_invite_suppliers_b_s, container, false)
        bottomSheetListner = erfxnewfragment as BottomSheetListner
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_add.setOnClickListener(View.OnClickListener {
            addDetails()

        })
    }

    private fun addDetails() {
        supplierName.setOnClickListener {
            tv_supplierName.visibility = View.VISIBLE
            val supplierName: String = supplierName.getText().toString()
            bottomSheetListner!!.onSaveClicked(supplierName)

        }
        contactPersonName.setOnClickListener {
            tv_supplierContactPersonName.visibility = View.VISIBLE
            val contactPersonName: String = contactPersonName.getText().toString()
            bottomSheetListner!!.onSaveClicked(contactPersonName)

        }
        tv_supplierMobileNo.setOnClickListener {
            tv_supplierMobileNo.visibility = View.VISIBLE
            val supplierMobileNo: String = supplierMobileNo.getText().toString()
            bottomSheetListner!!.onSaveClicked(supplierMobileNo)

        }
        tv_supplierEmail.setOnClickListener {
            tv_supplierEmail.visibility = View.VISIBLE
            val supplierEmail: String = supplierEmail.getText().toString()
            bottomSheetListner!!.onSaveClicked(supplierEmail)

        }

    }

    interface BottomSheetListner {
        fun onSaveClicked(string: String?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bottomSheetListner = context as BottomSheetListner
    }


}
