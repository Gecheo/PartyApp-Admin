package com.partyapp_admin.repositories

import com.google.firebase.auth.AuthResult
import com.partyapp_admin.data.LoginState
import com.partyapp_admin.data.RegisterLogin
import kotlinx.coroutines.flow.Flow

interface LoginRepo {
    suspend fun LoginAdmin(email:String, password:String): Flow<RegisterLogin<AuthResult>>
}