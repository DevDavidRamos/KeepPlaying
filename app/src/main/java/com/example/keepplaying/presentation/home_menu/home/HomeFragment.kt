package com.example.keepplaying.presentation.home_menu.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.keepplaying.R
import com.example.keepplaying.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val nombre = arguments?.getString("nombre")
        val apellido = arguments?.getString("apellido")

        binding.textView2.text = " Bienvenido/a:\n $nombre $apellido"

        initListener()
    }
    private fun initListener() {

        binding.ibtnPsn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToPlaystationFragment(nameQuery = "playstation")
            findNavController().navigate(action)
        }
        binding.ibtnXbox.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToXboxFragment(nameQuery = "xbox")
            findNavController().navigate(action)
        }
        binding.ibtnNintendo.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToNintendoFragment(nameQuery = "nintendo")
            findNavController().navigate(action)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
