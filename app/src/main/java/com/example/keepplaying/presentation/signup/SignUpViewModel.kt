package com.example.keepplaying.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keepplaying.firebase.domain.usecase.FirebaseSignUpUseCase
import com.example.keepplaying.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: FirebaseSignUpUseCase
) : ViewModel() {

    private val _signUpState: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val sigUpState: LiveData<Resource<Boolean>>
        get() = _signUpState


    fun signUp(nombre: String, apellido: String,email: String, password: String){
        viewModelScope.launch {
            signUpUseCase(nombre,apellido,email, password).onEach { state ->
                _signUpState.value = state
            }.launchIn(viewModelScope)
        }
    }



}