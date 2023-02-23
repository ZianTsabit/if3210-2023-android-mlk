package com.example.malika

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BranchAdapter(
    private val list: ArrayList<BranchItem>
) : RecyclerView.Adapter<BranchAdapter.BranchHolder>() {

    class BranchHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private var branchNameTextView: TextView? = null
        private var branchAddressTextView: TextView? = null
        private var branchPhoneNumberTextView: TextView? = null

        init {
            branchNameTextView = view.findViewById(R.id.branchNameTextView)
            branchAddressTextView = view.findViewById(R.id.branchAddressTextView)
            branchPhoneNumberTextView = view.findViewById(R.id.branchPhoneNumberTextView)
        }

        fun bind(data: BranchItem) {
            branchNameTextView?.text = data.name
            branchAddressTextView?.text = data.address
            branchPhoneNumberTextView?.text = data.phoneNumber
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchHolder {
        return BranchHolder(LayoutInflater.from(parent.context).inflate(R.layout.branch_card, parent, false))
    }

    override fun onBindViewHolder(holder: BranchHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}