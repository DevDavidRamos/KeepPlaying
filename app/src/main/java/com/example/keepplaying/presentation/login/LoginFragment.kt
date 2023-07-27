package com.example.keepplaying.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.keepplaying.R
import com.example.keepplaying.databinding.FragmentLoginBinding
import com.example.keepplaying.presentation.home_menu.home.HomeFragment
import com.example.keepplaying.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initListener()
    }

    private fun initObservers() {
        viewModel.loginState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Resource.Success -> {
                    handleLoading(isLoading = false)
                    val action =
                        LoginFragmentDirections.actionLoginFragmentToMenuMainActivity(
                            uid = state.data.uid,
                            nombre = state.data.nombre,
                            apellido = state.data.apellido
                        )
                    findNavController().navigate(action)



                }
                is Resource.Error -> {
                    handleLoading(isLoading = false)
                    Toast.makeText(
                        requireContext(),
                        state.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Loading -> handleLoading(isLoading = true)
                else -> Unit
            }
        }
    }

    private fun initListener() {
        with(binding) {
            btnLogin.setOnClickListener { handleLogin() }
            btnRegistro.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_createAccountFragment)
            }
            PasswordRecovery.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_passwordRecoveryFragment)
            }
        }
    }

    private fun handleLogin() {
        val email = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text.toString()

        viewModel.login(email, password)
    }

    private fun handleLoading(isLoading: Boolean) {
        with(binding) {
            if (isLoading) {
                btnLogin.text = ""
                btnLogin.isEnabled = false
                pbLogin.visibility = View.VISIBLE
            } else {
                pbLogin.visibility = View.GONE
                btnLogin.text = getString(R.string.login_button)
                btnLogin.isEnabled = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
