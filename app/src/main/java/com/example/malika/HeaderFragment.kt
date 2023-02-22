package com.example.malika

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HeaderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HeaderFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_header, container, false)
    }

    public fun changeTitle(title: String) {
        var headerView = view?.findViewById<TextView>(R.id.header_view)
        headerView?.text = title
    }

    public fun hideTemperature() {
        var temperatureTextView = view?.findViewById<TextView>(R.id.temperatureTextView)
        temperatureTextView?.visibility = View.INVISIBLE
    }

    public fun unhideTemperature() {
        var temperatureTextView = view?.findViewById<TextView>(R.id.temperatureTextView)
        temperatureTextView?.visibility = View.VISIBLE
    }

    public fun setTemperature(temperature: String) {
        var temperatureTextView = view?.findViewById<TextView>(R.id.temperatureTextView)
        var fillerTemperatureTextView = view?.findViewById<TextView>(R.id.fillerTemperatureTextView)

        fillerTemperatureTextView?.text = temperature
        temperatureTextView?.text = temperature
    }
}