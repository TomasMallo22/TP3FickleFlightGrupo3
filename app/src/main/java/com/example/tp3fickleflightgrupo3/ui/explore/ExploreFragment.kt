package com.example.tp3fickleflightgrupo3.ui.explore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp3fickleflightgrupo3.adapters.DestinationAdapter
import com.example.tp3fickleflightgrupo3.adapters.OfferAdapter
import com.example.tp3fickleflightgrupo3.adapters.OfferExploreAdapter
import com.example.tp3fickleflightgrupo3.data.model.Offer
import com.example.tp3fickleflightgrupo3.databinding.FragmentExploreBinding
import com.example.tp3fickleflightgrupo3.ui.flightResult.FlightResultsFragmentDirections

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private lateinit var destinationsAdapter: DestinationAdapter
    private lateinit var offersAdapter: OfferExploreAdapter

    private val viewModel: ExploreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar adaptadores
        destinationsAdapter = DestinationAdapter(emptyList())
        offersAdapter = OfferExploreAdapter(emptyList()) { offer ->
            val action = ExploreFragmentDirections.actionExploreFragmentToOfferFragment()
            findNavController().navigate(action)
        }

        // Configurar LayoutManager y adaptadores para RecyclerViews
        binding.recyclerTrendingDestinations.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = destinationsAdapter
        }

        binding.recyclerOffersExplore.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = offersAdapter
        }

        // Observa los datos del ViewModel
        observeViewModel()
    }

    private fun observeViewModel() {
        // Obtener y observar los datos de los destinos populares
        viewModel.trendingDestinations.observe(viewLifecycleOwner) { destinations ->
            Log.d("ExploreFragment", "Trending Destinations Observed: $destinations")
            destinations?.let {
                destinationsAdapter.updateDestinations(it)
            }
        }

        // Obtener y observar los datos de las ofertas
        viewModel.offers.observe(viewLifecycleOwner) { offers ->
            Log.d("ExploreFragment", "Offers Observed: $offers")
            offers?.let {
                offersAdapter.updateOffers(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
