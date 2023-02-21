package com.example.malika

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class MenuAdapter(
    private val list: ArrayList<MenuItem>
) : RecyclerView.Adapter<MenuAdapter.MenuHolder>() {

    class MenuHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private var menuNameTextView: TextView? = null
        private var menuPriceTextView: TextView? = null
        private var menuSoldTextView: TextView? = null
        private var menuDescriptionTextView: TextView? = null

        init {
            menuNameTextView = view.findViewById(R.id.menuNameTextView)
            menuPriceTextView = view.findViewById(R.id.menuPriceTextView)
            menuSoldTextView = view.findViewById(R.id.menuSoldTextView)
            menuDescriptionTextView = view.findViewById(R.id.menuDescriptionTextView)
        }

        fun bind(data: MenuItem) {
            menuNameTextView?.text = data.name
            menuPriceTextView?.text = data.currency  + " " +data.price.toString()
            menuSoldTextView?.text = data.sold.toString()
            menuDescriptionTextView?.text = data.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        return MenuHolder(LayoutInflater.from(parent.context).inflate(R.layout.menu_card, parent, false))
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}