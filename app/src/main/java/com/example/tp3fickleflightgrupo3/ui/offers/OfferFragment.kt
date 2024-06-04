package com.example.tp3fickleflightgrupo3.ui.offers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp3fickleflightgrupo3.adapters.OfferAdapter
import com.example.tp3fickleflightgrupo3.databinding.FragmentOfferBinding

class OfferFragment : Fragment() {

    private var _binding: FragmentOfferBinding? = null
    private val binding get() = _binding!!

    private lateinit var offersAdapter: OfferAdapter

    private val viewModel: OfferViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOfferBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar adaptador
        offersAdapter = OfferAdapter(emptyList())

        // Configurar LayoutManager y adaptador para RecyclerViews
        binding.recyclerOffers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = offersAdapter
        }

        // Observa los datos del ViewModel
        observeViewModel()
    }

    private fun observeViewModel() {
        // Obtener y observar los datos de las ofertas
        viewModel.offers.observe(viewLifecycleOwner) { offers ->
            Log.d("OfferFragment", "Offers Observed: $offers")
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
