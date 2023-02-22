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

        binding.foodRecyclerView.setHasFixedSize(true)
        binding.foodRecyclerView.isNestedScrollingEnabled = false
        binding.foodRecyclerView.layoutManager = LinearLayoutManager(context)

        binding.drinkRecyclerView.setHasFixedSize(true)
        binding.drinkRecyclerView.isNestedScrollingEnabled = false
        binding.drinkRecyclerView.layoutManager = LinearLayoutManager(context)

        val list = arrayListOf<MenuItem>(
            MenuItem("Nasi Goreng", "Deskripsi nasi goreng yang enak", "IDR", 10000, 20000, "Food"),
            MenuItem("Nasi Goreng 2", "Deskripsi nasi goreng yang sangat enak sekali", "IDR", 10000, 20000, "Food"),
            MenuItem("Es Teh Manis", "Deskripsi teh manis yang bikin diabetes", "IDR", 10000, 20000, "Drink"),
            MenuItem("Es Teh Tawar", "Deskripsi teh tawar yang gak bikin diabetes", "IDR", 10000, 20000, "Drink"),
            MenuItem("Indomie Goreng", "Indomie Seleraku", "IDR", 10000, 20000, "Food"),
            MenuItem("Es Teh Kopi", "Deskripsi es teh kopi yang enak", "IDR", 10000, 20000, "Drink"),
        )

        var foodList = list.filter { s -> s.type == "Food" } as ArrayList<MenuItem>
        var drinkList  = list.filter { s -> s.type == "Drink" } as ArrayList<MenuItem>

        var foodAdapter = MenuAdapter(foodList)
        foodAdapter.notifyDataSetChanged()
        binding.foodRecyclerView.adapter = foodAdapter

        var drinkAdapter = MenuAdapter(drinkList)
        foodAdapter.notifyDataSetChanged()
        binding.drinkRecyclerView.adapter = drinkAdapter

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