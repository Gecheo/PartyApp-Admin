package com.partyapp_admin.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.partyapp_admin.data.LoginState
import com.partyapp_admin.data.RegisterLogin
import com.partyapp_admin.repositories.LoginRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepo: LoginRepo
) : ViewModel() {

    private val _loginStatus = mutableStateOf(LoginState())
    val loginStatus = _loginStatus


    fun signingUser(email: String, password: String) = viewModelScope.launch {
        loginRepo.LoginAdmin(email, password).collect { result ->
            when (result) {
                is RegisterLogin.Success -> {
                    _loginStatus.value = LoginState(isSuccess = true)
                }

                is RegisterLogin.Loading -> {
                    _loginStatus.value = LoginState(isLoading = true)
                }

                is RegisterLogin.Error -> {
                    _loginStatus.value = LoginState(isError = result.message.toString())
                }
            }
        }
    }
}