package com.owescm.client.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.owescm.client.Model.HomeModel
import com.owescm.client.R
import com.owescm.client.activities.*

class HomeVerticalAdapter(
    private var mContext: Context?,
    private var homeList: ArrayList<HomeModel>
) : RecyclerView.Adapter<HomeVerticalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_home_fragment,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(homeList[position],position)
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvMenuTitle= itemView.findViewById<TextView>(R.id.menuTitle)
        private val rvItemDetails = itemView.findViewById<RecyclerView>(R.id.rv_itemDetails)

        private lateinit var homeHorizontalAdapter: HomeHorizontalAdapter

        fun setData(homeModel: HomeModel, mainPosition: Int) {
            tvMenuTitle.text = homeModel.menuTitle

            homeHorizontalAdapter = homeModel.let {
                HomeHorizontalAdapter(mContext, it.itemList, object : HomeHorizontalAdapter.ClickListener {
                        override fun onItemClick(position: Int, view: View) {
                            var intent: Intent? = null
                            val sharedView: View = view
                            var transitionName: String? = null
                            when (mainPosition) {
                                0 -> {
                                    intent = Intent(mContext, ErfxActivity::class.java)
                                    when (position) {
                                        0 -> {
                                            transitionName = "ErfxNew"
                                            intent.putExtra("from", "New")
                                        }
                                        1 -> {
                                            transitionName = "ErfxSaved"
                                            intent.putExtra("from", "Saved")
                                        }
                                        2 -> {
                                            transitionName = "ErfxLive"
                                            intent.putExtra("from", "Live")
                                        }
                                        3 -> {
                                            transitionName = "ErfxClosed"
                                            intent.putExtra("from", "Closed")
                                        }
                                    }
                                }
                                1 -> {
                                    intent = Intent(mContext, EAuctionActivity::class.java)
                                    when (position) {
                                        0 -> {
                                            transitionName = "EAuctionToday"
                                            intent.putExtra("from", "Today")
                                        }
                                        1 -> {
                                            transitionName = "EAuctionUpcoming"
                                            intent.putExtra("from", "Upcoming")
                                        }
                                        2 -> {
                                            transitionName = "EAuctionClosed"
                                            intent.putExtra("from", "Closed")
                                        }

                                    }
                                }
                                2 -> {
                                    intent = Intent(mContext, PrimaryEvaluationActivity::class.java)
                                    when (position) {
                                        0 -> {
                                            transitionName = "PEOpen"
                                            intent.putExtra("from", "Open")
                                        }
                                        1 -> {
                                            transitionName = "PEClosed"
                                            intent.putExtra("from", "Closed")
                                        }

                                    }
                                }
                                3 -> {
                                    intent = Intent(mContext, FinalEvaluationActivity::class.java)
                                    when (position) {
                                        0 -> {
                                            transitionName = "FEOpen"
                                            intent.putExtra("from", "Open")
                                        }
                                        1 -> {
                                            transitionName = "FEClosed"
                                            intent.putExtra("from", "Closed")
                                        }

                                    }
                                }
                                4 -> {
                                    intent = Intent(mContext, ContractActivity::class.java)
                                    when (position) {
                                        0 -> {
                                            transitionName = "ContractOpen"
                                            intent.putExtra("from", "Open")
                                        }
                                        1 -> {
                                            transitionName = "ContractClosed"
                                            intent.putExtra("from", "Closed")
                                        }

                                    }
                                }
                                5 -> {
                                    intent = Intent(mContext, SpendManagementActivity::class.java)
                                    when (position) {
                                        0 -> {
                                            transitionName = "SMReports"
                                            intent.putExtra("from", "Reports")
                                        }
                                        1 -> {
                                            transitionName = "SMStatus"
                                            intent.putExtra("from", "Status")
                                        }

                                    }
                                }
                            }

                            val transitionActivityOptions =
                                ActivityOptions.makeSceneTransitionAnimation(
                                    mContext as Activity,
                                    sharedView, transitionName
                                )
                            mContext?.startActivity(intent, transitionActivityOptions.toBundle())
                        }
                })
            }
            rvItemDetails.apply {
                adapter = homeHorizontalAdapter
                layoutManager = GridLayoutManager(context,4)
            }

        }
    }


}



