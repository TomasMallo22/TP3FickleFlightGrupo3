package com.example.tp3fickleflightgrupo3.ui.explore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp3fickleflightgrupo3.adapters.TrendingDestinationsAdapter
import com.example.tp3fickleflightgrupo3.adapters.OfferExploreAdapter
import com.example.tp3fickleflightgrupo3.databinding.FragmentExploreBinding

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private lateinit var trendingDestinationsAdapter: TrendingDestinationsAdapter
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
        trendingDestinationsAdapter = TrendingDestinationsAdapter(emptyList())
        offersAdapter = OfferExploreAdapter(emptyList())

        // Configurar LayoutManager y adaptadores para RecyclerViews
        binding.recyclerTrendingDestinations.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = trendingDestinationsAdapter
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
                trendingDestinationsAdapter.updateDestinations(it)
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
