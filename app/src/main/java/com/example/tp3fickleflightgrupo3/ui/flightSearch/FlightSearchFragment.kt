package com.example.tp3fickleflightgrupo3.ui.flightSearch

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.tp3fickleflightgrupo3.databinding.FragmentFlightSearchBinding
import com.google.gson.Gson
import java.util.Calendar

class FlightSearchFragment : Fragment() {

    private lateinit var binding: FragmentFlightSearchBinding
    private val viewModel: FlightSearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val classes = arrayOf("Economy", "Business", "First Class")
        val classAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, classes)
        binding.etClass.setAdapter(classAdapter)

        setupDatePicker(binding.etDepartureDate)
        setupDatePicker(binding.etReturnDate)

        binding.btnSearch.setOnClickListener {
            if (validateInputs()) {
                viewModel.searchFlights()
            }
        }

        viewModel.flights.observe(viewLifecycleOwner, Observer { flights ->
            flights?.let {
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
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        })
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

    private fun validateInputs(): Boolean {
        var isValid = true

        if (binding.etDeparture.text.isEmpty()) {
            binding.etDeparture.error = "Departure is required"
            isValid = false
        }
        if (binding.etArrival.text.isEmpty()) {
            binding.etArrival.error = "Arrival is required"
            isValid = false
        }
        if (binding.etDepartureDate.text.isEmpty()) {
            binding.etDepartureDate.error = "Departure date is required"
            isValid = false
        }
        if (binding.etPassengers.text.isEmpty()) {
            binding.etPassengers.error = "Number of passengers is required"
            isValid = false
        }
        if (binding.etClass.text.isEmpty()) {
            binding.etClass.error = "Class is required"
            isValid = false
        }

        return isValid
    }
}
