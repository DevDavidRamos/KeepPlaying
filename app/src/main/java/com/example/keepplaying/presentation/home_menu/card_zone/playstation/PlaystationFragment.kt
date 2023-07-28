package com.example.keepplaying.presentation.home_menu.card_zone.playstation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.keepplaying.R
import com.example.keepplaying.databinding.FragmentNintendoBinding
import com.example.keepplaying.databinding.FragmentPlaystationBinding


class PlaystationFragment : Fragment() {
    private var _binding: FragmentPlaystationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaystationBinding.inflate(inflater, container, false)
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
