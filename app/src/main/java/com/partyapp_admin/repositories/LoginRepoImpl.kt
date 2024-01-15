package com.partyapp_admin.repositories

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.partyapp_admin.data.RegisterLogin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginRepoImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): LoginRepo {
    override suspend fun LoginAdmin(
        email: String,
        password: String
    ): Flow<RegisterLogin<AuthResult>> {
        return flow {
            emit(RegisterLogin.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email,password)
                .await()
            emit(RegisterLogin.Success(result))
        }
            .catch {
                emit(RegisterLogin.Error(it.message.toString()))
            }
    }
}