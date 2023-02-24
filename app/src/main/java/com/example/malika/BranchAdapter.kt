package com.example.malika

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BranchAdapter(
    private var list: ArrayList<BranchItem>,
    private val context: Context,
) : RecyclerView.Adapter<BranchAdapter.BranchHolder>() {


    class BranchHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private var branchNameTextView: TextView? = null
        private var branchAddressTextView: TextView? = null
        private var branchPhoneNumberTextView: TextView? = null
        private var mapsButton: Button? = null

        init {
            branchNameTextView = view.findViewById(R.id.branchNameTextView)
            branchAddressTextView = view.findViewById(R.id.branchAddressTextView)
            branchPhoneNumberTextView = view.findViewById(R.id.branchPhoneNumberTextView)
            mapsButton = view.findViewById(R.id.mapsButton)
        }

        fun bind(data: BranchItem, context: Context) {
            branchNameTextView?.text = data.name
            branchAddressTextView?.text = data.address
            branchPhoneNumberTextView?.text = data.phoneNumber
            mapsButton?.setOnClickListener { v ->
                val gmmIntentUri = Uri.parse("geo:${data.latitude},${data.langitude}?q=${data.address}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                context.startActivity(mapIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchHolder {
        return BranchHolder(LayoutInflater.from(parent.context).inflate(R.layout.branch_card, parent, false))
    }

    override fun onBindViewHolder(holder: BranchHolder, position: Int) {
        holder.bind(list[position], context)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    fun updateBranchList(branchList: ArrayList<BranchItem>) {
        list.clear()
        branchList.sortBy { it.name }
        list = branchList
        notifyDataSetChanged()
    }
}