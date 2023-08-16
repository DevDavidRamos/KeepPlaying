package com.example.keepplaying.presentation.home_menu.card_zone.xbox

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.keepplaying.R
import com.example.keepplaying.databinding.FragmentNintendoBinding
import com.example.keepplaying.databinding.FragmentXboxBinding
import com.example.keepplaying.firebase.domain.model.Product
import com.example.keepplaying.presentation.home_menu.result.ProductListAdapter
import com.example.keepplaying.presentation.home_menu.result.SearchResultFragmentArgs
import com.example.keepplaying.presentation.home_menu.result.SearchResultViewModel
import com.example.keepplaying.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class XboxFragment : Fragment() {
    private var _binding: FragmentXboxBinding? = null
    private val binding get() = _binding!!
    private val viewModel: XboxViewModel by viewModels()
    private val args: XboxFragmentArgs by navArgs()
    private val productAdapter: ProductListAdapter = ProductListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentXboxBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnback.setOnClickListener { activity?.onBackPressed() }




        setUiComponents()
        setObservers()
        setClickListeners()
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productsFlow.collect { productsResult ->
                    when (productsResult) {
                        is UiState.Success -> {
                            handleLoading(isLoading = false)
                            handleSuccessSearch(products = productsResult.data)
                        }
                        is UiState.Error -> {
                            handleLoading(isLoading = false)
                            showEmptyScreen(shouldShow = true, message = getString(R.string.search_result__error_disclaimer))
                        }
                        is UiState.Loading -> {
                            handleLoading(isLoading = true)
                        }
                        else -> Unit
                    }
                }
            }
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.rvXbox.visibility = if (isLoading) View.GONE else View.VISIBLE
        binding.lsLoadingScreen.root.apply {
            visibility = if (isLoading) {
                startShimmer()
                View.VISIBLE
            } else {
                stopShimmer()
                View.GONE
            }
        }
    }

    private fun handleSuccessSearch(products: List<Product>) {

        if (products.isEmpty()) {
            showEmptyScreen(shouldShow = true)
        }


        productAdapter.submitList(products)
    }


    private fun showEmptyScreen(message: String? = null, shouldShow: Boolean) {
        binding.lsEmptyScreen.root.visibility = if (shouldShow) View.VISIBLE else View.GONE
        if (message != null) {
            binding.lsEmptyScreen.tvMessage.text = message
        }
    }

    private fun setUiComponents() {
        binding.textView6.text = args.nameQuery
        binding.rvXbox.apply {
            adapter = productAdapter

        }
    }

    private fun setClickListeners() {
        productAdapter.apply {
            setProductClickListener {
                Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
            }

            setFavoriteClickListener {
                if (it.isFavorite) {
                    viewModel.deleteFavorite(it.id)
                } else {
                    viewModel.saveFavorite(it.id)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
