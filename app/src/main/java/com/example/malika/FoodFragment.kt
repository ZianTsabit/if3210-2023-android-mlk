package com.example.malika

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.malika.databinding.FragmentFoodBinding


class FoodFragment : Fragment() {
    private lateinit var viewModel: MenuViewModel
    private lateinit var mCartViewModel: CartViewModel

    private var _binding : FragmentFoodBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFoodBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        mCartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        binding.foodRecyclerView.setHasFixedSize(true)
        binding.foodRecyclerView.isNestedScrollingEnabled = false
        binding.foodRecyclerView.layoutManager = LinearLayoutManager(context)

        binding.drinkRecyclerView.setHasFixedSize(true)
        binding.drinkRecyclerView.isNestedScrollingEnabled = false
        binding.drinkRecyclerView.layoutManager = LinearLayoutManager(context)

        var foodAdapter = MenuAdapter(ArrayList<MenuItem>())
        foodAdapter.notifyDataSetChanged()
        binding.foodRecyclerView.adapter = foodAdapter

        var drinkAdapter = MenuAdapter(ArrayList<MenuItem>())
        foodAdapter.notifyDataSetChanged()
        binding.drinkRecyclerView.adapter = drinkAdapter

        val foodListUpdateObserver: Observer<ArrayList<MenuItem>> =
            Observer<ArrayList<MenuItem>> { foodList ->
                foodAdapter.updateMenuList(foodList)
            }

        val drinkListUpdateObserver: Observer<ArrayList<MenuItem>> =
            Observer<ArrayList<MenuItem>> { drinkList ->
                drinkAdapter.updateMenuList(drinkList)
            }

        viewModel.foodList.observe(viewLifecycleOwner, foodListUpdateObserver)
        viewModel.drinkList.observe(viewLifecycleOwner, drinkListUpdateObserver)

        viewModel.getMenu()

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