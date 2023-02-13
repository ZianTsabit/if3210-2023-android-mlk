package com.example.malika

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.malika.databinding.FragmentBucketBinding
import com.example.malika.databinding.FragmentFoodBinding


class FoodFragment : Fragment() {

    private lateinit var mCartViewModel: CartViewModel

    private var _binding : FragmentFoodBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFoodBinding.inflate(inflater, container, false)

        mCartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        binding.btnAddItem.setOnClickListener{
            addItem()
        }

        return  binding.root
    }


    private fun addItem() {

        val item_name = "Ayam Bakar"
        val item_price = 15000
        val quantity = 1

        val item = Item(item_name, item_price, quantity)
        mCartViewModel.addItem(item)
        Toast.makeText(requireContext(), "Foor/Drink successfully added!", Toast.LENGTH_LONG).show()
    }

}