package com.example.tp3fickleflightgrupo3.ui.flightResult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp3fickleflightgrupo3.R
import com.example.tp3fickleflightgrupo3.adapters.FlightAdapter
import com.example.tp3fickleflightgrupo3.databinding.FragmentFlightResultBinding

class FlightResultsFragment : Fragment() {

    private lateinit var binding: FragmentFlightResultBinding
    private val viewModel: FlightResultViewModel by viewModels()
    private lateinit var flightAdapter: FlightAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flightAdapter = FlightAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = flightAdapter


        viewModel.flights.observe(viewLifecycleOwner, Observer { flights ->
            flights?.let {
                flightAdapter.submitList(it)
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                "Error en Flight Result"
            }
        })

        viewModel.searchFlights()

        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_flightResultsFragment_to_flightSearchFragment)
        }
    }
}
