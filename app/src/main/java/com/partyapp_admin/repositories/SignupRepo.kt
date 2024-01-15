package com.partyapp_admin.repositories

import com.google.firebase.auth.AuthResult
import com.partyapp_admin.data.AdminDetails
import com.partyapp_admin.data.RegisterLogin
import kotlinx.coroutines.flow.Flow

interface SignupRepo{
    suspend fun SignUp(adminDetails: AdminDetails) :Flow<RegisterLogin<AuthResult>>
}