package com.example.malika

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.malika.databinding.FragmentBucketBinding

class BucketFragment : Fragment(), OnItemUpdateListener {

    private var _binding : FragmentBucketBinding? = null
    private val binding get() = _binding!!
    private lateinit var mCartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBucketBinding.inflate(inflater, container, false)

        // Recyclerview
        val adapter = BucketAdapter()
        val recyclerView = binding.cartView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // CartViewModel
        mCartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        mCartViewModel.readAllItem.observe(viewLifecycleOwner, Observer { item ->
            adapter.setData(item, this)
        } )

        val totalPrice = Observer<Int> { newTotal ->
            if(newTotal === null) {
                binding.totalPrice.text = "Total: 0"
            }else {
                binding.totalPrice.text = "Total: IDR $newTotal"
            }
        }

        mCartViewModel.totalPrice.observe(viewLifecycleOwner, totalPrice)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnScanQR.setOnClickListener {
            if (binding.totalPrice.text != "Total: 0") {
                scanQRCode()
            }else{
                Toast.makeText(requireContext(), "Keranjang kamu kosong", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun scanQRCode() {

        val intent = Intent(context, CodeScannerActivity::class.java)
        startActivity(intent)

    }

    override fun OnItemUpdated(item: Item, position: Int) {

        val id = item.id
        val amount = item.amount.plus(1)
        val price = item.price
        val name = item.name

        val updatedItem = Item(id, name, price, amount)
        mCartViewModel.updateItem(updatedItem)
    }

    override fun onItemDecrease(item: Item, position: Int) {

        val id = item.id
        val amount = item.amount.minus(1)
        val price = item.price
        val name = item.name

        if(amount == 0){
            mCartViewModel.deleteItem(item)
        }else{
            val updatedItem = Item(id, name, price, amount)
            mCartViewModel.updateItem(updatedItem)
        }

    }
}