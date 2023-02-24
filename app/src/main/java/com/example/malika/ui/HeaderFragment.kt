package com.example.malika.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.malika.R

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