package com.example.tp3fickleflightgrupo3.ui.flightSearch

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp3fickleflightgrupo3.R
import com.example.tp3fickleflightgrupo3.adapters.OfferExploreAdapter
import com.example.tp3fickleflightgrupo3.databinding.FragmentFlightSearchBinding
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import java.util.Calendar

class FlightSearchFragment : Fragment() {

    private lateinit var binding: FragmentFlightSearchBinding
    private val viewModel: FlightSearchViewModel by viewModels()
    private lateinit var offersAdapter: OfferExploreAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightSearchBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnOneWay = view.findViewById<MaterialButton>(R.id.btnOneWay)
        val btnRoundTrip = view.findViewById<MaterialButton>(R.id.btnRoundTrip)
        val etClass = binding.etClass

        setupDatePicker(binding.etDepartureDate)
        setupDatePicker(binding.etReturnDate)

        btnOneWay.setOnClickListener {
            toggleButtons(btnOneWay, btnRoundTrip)
            binding.etReturnDate.visibility = View.GONE
            binding.ivReturnDateIcon.visibility = View.GONE
        }

        btnRoundTrip.setOnClickListener {
            toggleButtons(btnRoundTrip, btnOneWay)
            binding.etReturnDate.visibility = View.VISIBLE
            binding.ivReturnDateIcon.visibility = View.VISIBLE
        }

        binding.btnSearch.setOnClickListener {
            if (validateInputs()) {
                viewModel.searchFlights()
            }
        }

        viewModel.flights.observe(viewLifecycleOwner, Observer { flights ->
            if (flights != null) {
                val gson = Gson()
                val flightsJson = gson.toJson(flights)
                val action =
                    FlightSearchFragmentDirections.actionFlightSearchFragmentToFlightResultsFragment(
                        flightsJson
                    )
                findNavController().navigate(action)
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                // Show error message
            }
        })

        offersAdapter = OfferExploreAdapter(emptyList()) { offer ->
            val action = FlightSearchFragmentDirections.actionFlightSearchFragmentToOfferFragment()
            findNavController().navigate(action)
        }

        binding.recyclerOffersExplore.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = offersAdapter
        }

        viewModel.offers.observe(viewLifecycleOwner, Observer { offers ->
            offers?.let {
                offersAdapter.updateOffers(it)
                offersAdapter.notifyDataSetChanged()
            }
        })

        // Setup the class dropdown
        val classes = arrayOf("Economy", "Business", "First Class")
        val classAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, classes)
        etClass.setAdapter(classAdapter)
    }

    private fun setupDatePicker(editText: EditText) {
        editText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog =
                DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                    val date = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                    editText.setText(date)
                }, year, month, day)

            datePickerDialog.show()
        }
    }

    private fun toggleButtons(selectedButton: MaterialButton, unselectedButton: MaterialButton) {
        selectedButton.setBackgroundColor(resources.getColor(R.color.selected_button_color))
        selectedButton.setTextColor(resources.getColor(R.color.white))
        unselectedButton.setBackgroundColor(resources.getColor(R.color.unselected_button_color))
        unselectedButton.setTextColor(resources.getColor(R.color.black))
    }

    private fun validateInputs(): Boolean {
        var isValid = true

        if (binding.etDeparture.text.isNullOrEmpty()) {
            binding.etDeparture.error = "Departure is required"
            isValid = false
        }

        if (binding.etArrival.text.isNullOrEmpty()) {
            binding.etArrival.error = "Arrival is required"
            isValid = false
        }

        if (binding.etDepartureDate.text.isNullOrEmpty()) {
            binding.etDepartureDate.error = "Departure date is required"
            isValid = false
        }

        if (binding.etPassengers.text.isNullOrEmpty()) {
            binding.etPassengers.error = "Number of passengers is required"
            isValid = false
        }

        if (binding.etClass.text.isNullOrEmpty()) {
            binding.etClass.error = "Class is required"
            isValid = false
        }

        return isValid
    }
}
