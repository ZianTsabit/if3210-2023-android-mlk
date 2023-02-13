package com.example.malika

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.malika.databinding.FragmentBucketBinding

class BucketFragment : Fragment() {

    private var _binding : FragmentBucketBinding? = null
    private val binding get() = _binding!!

    private lateinit var mCartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBucketBinding.inflate(inflater, container, false)

        // Recycleview
        val adapter = BucketAdapter()
        val recyclerView = binding.cartView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // CartViewModel
        mCartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        mCartViewModel.readAllItem.observe(viewLifecycleOwner, Observer { item ->
            adapter.setData(item)
        } )

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnScanQR.setOnClickListener {
            scanQRCode()
        }


    }

    private fun scanQRCode() {

        val intent = Intent(context, CodeScannerActivity::class.java)
        startActivity(intent)

    }

    private fun updateItemAmount(){




    }

    private fun deleteItem() {


    }
}