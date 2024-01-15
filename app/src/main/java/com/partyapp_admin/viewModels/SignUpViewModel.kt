package com.partyapp_admin.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.partyapp_admin.data.AdminDetails
import com.partyapp_admin.data.RegisterLogin
import com.partyapp_admin.data.SignUpState
import com.partyapp_admin.repositories.SignupRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signupRepo: SignupRepo
) : ViewModel() {


    private val _signUpStatus = Channel<SignUpState>()
    val signUpStatus = _signUpStatus.receiveAsFlow()


    fun registerAdmin(admin: AdminDetails) = viewModelScope.launch {
        signupRepo.SignUp(admin).collect { result ->
            when (result) {
                is RegisterLogin.Success -> {
                    _signUpStatus.send(SignUpState(isSuccess = true))
                }

                is RegisterLogin.Loading -> {
                    _signUpStatus.send(SignUpState(isLoading = true))
                }

                is RegisterLogin.Error -> {
                    _signUpStatus.send(SignUpState(isError = result.message.toString()))
                }
            }
        }
    }


}