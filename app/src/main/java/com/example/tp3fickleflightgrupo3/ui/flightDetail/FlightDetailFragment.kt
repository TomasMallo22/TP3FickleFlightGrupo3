package com.example.tp3fickleflightgrupo3.ui.flightDetail

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp3fickleflightgrupo3.R
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

        viewModel.flightDetails.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        })

        binding.bookNowButton.setOnClickListener {
            showReservationSuccessMessage()
        }

        setupPhotoRecyclerView()

        binding.backArrow.setOnClickListener {
            findNavController().navigateUp()
        }

        val favIcon = view.findViewById<ImageView>(R.id.favIcon)
        favIcon.setOnClickListener {
            it.isSelected = !it.isSelected
        }
    }

    private fun setupPhotoRecyclerView() {
        val photos = listOf(R.drawable.photo, R.drawable.photo_1, R.drawable.photo_2)
        binding.photoRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = PhotoAdapter(photos)
            setHasFixedSize(true)
        }
    }


    private fun showReservationSuccessMessage() {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.msg_vuelo))
            .create()
        alertDialog.show()

        Handler().postDelayed({
            alertDialog.dismiss()
        }, 3000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
