package com.example.malika

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.malika.databinding.ActivityMainBinding
import com.example.malika.databinding.BucketRowBinding

class BucketAdapter: RecyclerView.Adapter<BucketAdapter.CartViewHolder>() {

    private var itemList = emptyList<Item>()

    class CartViewHolder(private val itemBinding: BucketRowBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Item){
            itemBinding.itemName.text = item.name
            itemBinding.itemPrice.text = "Rp " + item.price.toString()
            itemBinding.itemQuantity.text = item.amount.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemBinding = BucketRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bind(currentItem)
    }

    fun setData(item: List<Item>){
        this.itemList = item
        notifyDataSetChanged()
    }

}