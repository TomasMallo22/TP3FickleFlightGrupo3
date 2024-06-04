package com.example.tp3fickleflightgrupo3.ui.drawerMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tp3fickleflightgrupo3.databinding.FragmentDrawerMenuBinding

class DrawerMenuFragment : Fragment() {

    private var _binding: FragmentDrawerMenuBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DrawerMenuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDrawerMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.menuProfile.setOnClickListener {

        }

        binding.menuNotifications.setOnClickListener {

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
