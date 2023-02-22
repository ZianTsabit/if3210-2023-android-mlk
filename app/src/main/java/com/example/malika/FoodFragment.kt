package com.example.malika

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

        binding.menuRecyclerView.setHasFixedSize(true)

        binding.menuRecyclerView.layoutManager = LinearLayoutManager(context)

        val list = ArrayList<MenuItem>()

        for (i in 0 until 3) {

            list.add(MenuItem("Nasi Goreng", "Deskripsi nasi goreng yang enak", "IDR", 10000, 20000, "food"))

            if(3 - 1 == i){
                // init adapter yang telah dibuat tadi
                val adapter = MenuAdapter(list)
                adapter.notifyDataSetChanged()

                //tampilkan data dalam recycler view
                binding.menuRecyclerView.adapter = adapter
            }

        }

//        binding.btnAddItem.setOnClickListener{
//            addItem()
//        }

        return  binding.root
    }

    private fun addItem() {

        val item_name = "Ayam Bakar"
        val item_price = 15000
        val quantity = 1

        val item = Item(0,item_name, item_price, quantity)
        mCartViewModel.addItem(item)
        Toast.makeText(requireContext(), "Foor/Drink successfully added!", Toast.LENGTH_LONG).show()
    }

}