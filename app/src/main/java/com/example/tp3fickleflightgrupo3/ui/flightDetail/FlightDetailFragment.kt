package com.example.tp3fickleflightgrupo3.ui.flightDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.tp3fickleflightgrupo3.databinding.FragmentFlightDetailBinding

class FlightDetailFragment : Fragment() {
    private var _binding: FragmentFlightDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FlightDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlightDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchFlightDetails()
        // Observe ViewModel and bind data
        viewModel.flightDetails.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
        })
        // Observe for errors
        viewModel.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        })
        // Set button click listener
        binding.bookNowButton.setOnClickListener {
            // Handle button click
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}