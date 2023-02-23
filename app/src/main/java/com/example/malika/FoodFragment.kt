package com.example.malika

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.malika.databinding.FragmentFoodBinding


class FoodFragment : Fragment(), OnMenuItemUpdateListener {
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

        val cartItemsUpdateObserver: Observer<List<Item>> =
            Observer<List<Item>> { itemList ->
                Log.i("MENU", "Update amount")
                viewModel.updateAmount(itemList)
            }

        val foodListUpdateObserver: Observer<ArrayList<MenuItem>> =
            Observer<ArrayList<MenuItem>> { foodList ->
                foodAdapter.updateMenuList(foodList, this)
            }

        val drinkListUpdateObserver: Observer<ArrayList<MenuItem>> =
            Observer<ArrayList<MenuItem>> { drinkList ->
                drinkAdapter.updateMenuList(drinkList, this)
            }

        mCartViewModel.readAllItem.observe(viewLifecycleOwner, cartItemsUpdateObserver)
        viewModel.foodList.observe(viewLifecycleOwner, foodListUpdateObserver)
        viewModel.drinkList.observe(viewLifecycleOwner, drinkListUpdateObserver)

        _binding!!.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchQuery = newText
                viewModel.search()
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchQuery = query
                viewModel.search()
                return false
            }

        })

        viewModel.getMenu()
        
        return  binding.root
    }

    override fun OnItemUpdated(item: MenuItem, position: Int) {
        if (item.amount == 0) {
            Log.i("MENU", "Add started")

            val item = Item(0, item.name, item.price, 1)
            mCartViewModel.addItem(item)
        } else {
            Log.i("MENU", "Update started")

            var findItem = mCartViewModel.getByNameAndPrice(item.name, item.price)

            Log.i("MENU", "findItem = $findItem")

            if (findItem != null) {
                val updatedItem = findItem?.let { Item(it.id, it.name, item.price, it.amount.plus(1)) }
                mCartViewModel.updateItem(updatedItem as Item)
            }
        }
    }

    override fun onItemDecrease(item: MenuItem, position: Int) {
        if (item.amount == 1) {
            Log.i("MENU", "Remove started")

            var findItem = mCartViewModel.getByNameAndPrice(item.name, item.price)

            Log.i("MENU", "findItem = $findItem")

            if (findItem != null) {
                val deletedItem = findItem?.let { Item(it.id, it.name, item.price, it.amount.minus(1)) }
                mCartViewModel.deleteItem(deletedItem as Item)
            }

        } else if (item.amount > 1) {
            Log.i("MENU", "Reduce started")

            var findItem = mCartViewModel.getByNameAndPrice(item.name, item.price)

            Log.i("MENU", "findItem = $findItem")

            if (findItem != null) {
                val updatedItem = findItem?.let { Item(it.id, it.name, item.price, it.amount.minus(1)) }
                mCartViewModel.updateItem(updatedItem as Item)
            }
        }
    }

}