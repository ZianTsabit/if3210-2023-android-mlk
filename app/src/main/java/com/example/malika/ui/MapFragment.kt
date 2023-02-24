package com.example.malika.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.malika.*
import com.example.malika.adapters.BranchAdapter
import com.example.malika.databinding.FragmentMapBinding
import com.example.malika.domain.BranchItem

/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFragment : Fragment() {
    private lateinit var viewModel: BranchViewModel

    private var _binding : FragmentMapBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMapBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(BranchViewModel::class.java)

        binding.branchRecyclerView.setHasFixedSize(true)
        binding.branchRecyclerView.layoutManager = LinearLayoutManager(context)

        var adapter = BranchAdapter(ArrayList(), requireContext())
        adapter.notifyDataSetChanged()
        binding.branchRecyclerView.adapter = adapter

        val branchListUpdateObserver: Observer<ArrayList<BranchItem>> =
            Observer<ArrayList<BranchItem>> { branchList ->
                adapter.updateBranchList( branchList)
            }

        viewModel.branchList.observe(viewLifecycleOwner, branchListUpdateObserver)

        viewModel.getBranch()

        return  binding.root
    }
}