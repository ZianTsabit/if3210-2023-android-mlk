package com.example.malika.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.malika.ui.OnMenuItemUpdateListener
import com.example.malika.R
import com.example.malika.domain.MenuItem

class MenuAdapter(
    private var list: ArrayList<MenuItem>
) : RecyclerView.Adapter<MenuAdapter.MenuHolder>() {

    private lateinit var onMenuItemUpdateListener: OnMenuItemUpdateListener
    class MenuHolder(val view: View, private val onMenuItemUpdateListener: OnMenuItemUpdateListener) : RecyclerView.ViewHolder(view) {
        private var menuNameTextView: TextView? = null
        private var menuPriceTextView: TextView? = null
        private var menuSoldTextView: TextView? = null
        private var menuDescriptionTextView: TextView? = null
        private var menuOrderAmount: TextView? = null
        private var menuAddButton: ImageButton? = null
        private var menuReduceButton: ImageButton? = null

        init {
            menuNameTextView = view.findViewById(R.id.menuNameTextView)
            menuPriceTextView = view.findViewById(R.id.menuPriceTextView)
            menuSoldTextView = view.findViewById(R.id.menuSoldTextView)
            menuDescriptionTextView = view.findViewById(R.id.menuDescriptionTextView)
            menuOrderAmount = view.findViewById(R.id.menuOrderAmount)
            menuAddButton = view.findViewById(R.id.menuAddButton)
            menuReduceButton = view.findViewById(R.id.menuReduceButton)
        }

        fun bind(data: MenuItem) {
            menuNameTextView?.text = data.name
            menuPriceTextView?.text = data.currency  + " " +data.price.toString()
            menuSoldTextView?.text = "${data.sold.toString()} terjual"
            menuDescriptionTextView?.text = data.description
            menuOrderAmount?.text = data.amount.toString()

            menuAddButton?.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    onMenuItemUpdateListener.OnItemUpdated(data, pos)
                }
            }

            menuReduceButton?.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    onMenuItemUpdateListener.onItemDecrease(data, pos)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        return MenuHolder(LayoutInflater.from(parent.context).inflate(R.layout.menu_card, parent, false), onMenuItemUpdateListener)
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    fun updateMenuList(menuList: ArrayList<MenuItem>, onMenuItemUpdateListener: OnMenuItemUpdateListener) {
        list.clear()
        list = menuList
        this.onMenuItemUpdateListener = onMenuItemUpdateListener
        notifyDataSetChanged()
    }
}