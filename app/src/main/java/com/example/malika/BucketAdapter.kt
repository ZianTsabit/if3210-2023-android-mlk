package com.example.malika

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.malika.databinding.BucketRowBinding

class BucketAdapter: RecyclerView.Adapter<BucketAdapter.CartViewHolder>() {

    private var itemList = emptyList<Item>()
    private lateinit var onItemUpdateListener: OnItemUpdateListener

    class CartViewHolder(private val itemBinding: BucketRowBinding, private val onItemUpdateListener: OnItemUpdateListener): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Item){
            itemBinding.itemName.text = item.name
            itemBinding.itemPrice.text = "IDR " + item.price.toString()
            itemBinding.itemQuantity.text = item.amount.toString()

            itemBinding.plusButton.setOnClickListener {
                if (onItemUpdateListener != null) {
                    val pos = adapterPosition
                    if (pos != RecyclerView.NO_POSITION) {
                        onItemUpdateListener.OnItemUpdated(item, pos)
                    }
                }
            }

            itemBinding.minusButton.setOnClickListener {
                if (onItemUpdateListener != null) {
                    val pos = adapterPosition
                    if (pos != RecyclerView.NO_POSITION) {
                        onItemUpdateListener.onItemDecrease(item, pos)
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemBinding = BucketRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(itemBinding, onItemUpdateListener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bind(currentItem)
    }

    fun setData(item: List<Item>, onItemUpdateListener: OnItemUpdateListener){
        this.itemList = item
        this.onItemUpdateListener = onItemUpdateListener
        notifyDataSetChanged()
    }

}