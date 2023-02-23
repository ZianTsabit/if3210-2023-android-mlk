package com.example.malika

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.malika.databinding.FragmentMapBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentMapBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMapBinding.inflate(inflater, container, false)

        binding.branchRecyclerView.setHasFixedSize(true)
        binding.branchRecyclerView.layoutManager = LinearLayoutManager(context)

        val list = arrayListOf<BranchItem>(
            BranchItem("Anchorage", "Swiss Chard", "12022 Town Park Circle", "Lady Destini Bechtelar", "397-110-6582", -149.5778193, 61.32927710000001),
            BranchItem("Anchorage", "Swiss Chard", "12022 Town Park Circle", "Lady Destini Bechtelar", "397-110-6582", -149.5778193, 61.32927710000001),
            BranchItem("Anchorage", "Swiss Chard", "12022 Town Park Circle", "Lady Destini Bechtelar", "397-110-6582", -149.5778193, 61.32927710000001),
            BranchItem("Anchorage", "Swiss Chard", "12022 Town Park Circle", "Lady Destini Bechtelar", "397-110-6582", -149.5778193, 61.32927710000001),
            BranchItem("Anchorage", "Swiss Chard", "12022 Town Park Circle", "Lady Destini Bechtelar", "397-110-6582", -149.5778193, 61.32927710000001),
            BranchItem("Anchorage", "Swiss Chard", "12022 Town Park Circle", "Lady Destini Bechtelar", "397-110-6582", -149.5778193, 61.32927710000001),
        )

        requireActivity()
        var adapter = BranchAdapter(list, requireContext())
        adapter.notifyDataSetChanged()
        binding.branchRecyclerView.adapter = adapter

//        binding.btnAddItem.setOnClickListener{
//            addItem()
//        }

        return  binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MapFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}