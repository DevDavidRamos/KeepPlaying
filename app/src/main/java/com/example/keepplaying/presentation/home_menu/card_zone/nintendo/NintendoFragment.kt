package com.example.keepplaying.presentation.home_menu.card_zone.nintendo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.keepplaying.R
import com.example.keepplaying.databinding.FragmentHomeBinding
import com.example.keepplaying.databinding.FragmentNintendoBinding


class NintendoFragment : Fragment() {

        private var _binding: FragmentNintendoBinding? = null
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            _binding = FragmentNintendoBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

        binding.btnback.setOnClickListener { activity?.onBackPressed() }

        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
