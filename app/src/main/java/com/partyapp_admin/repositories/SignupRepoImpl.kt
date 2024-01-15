package com.partyapp_admin.repositories

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.partyapp_admin.data.AdminDetails
import com.partyapp_admin.data.RegisterLogin
import com.partyapp_admin.utils.ADMIN
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class SignupRepoImpl @Inject constructor(
    private val firebaseAuth : FirebaseAuth,
    private val firestore: FirebaseFirestore
): SignupRepo {
    override suspend fun SignUp(adminDetails: AdminDetails): Flow<RegisterLogin<AuthResult>>
    = callbackFlow {
        trySend(RegisterLogin.Loading())
        firebaseAuth.createUserWithEmailAndPassword(adminDetails.email,adminDetails.password)
            .addOnSuccessListener {
                val userId = it.user?.uid
                firestore.collection(ADMIN).document(userId!!).set(adminDetails)
                trySend(RegisterLogin.Success(it))
            }
            .addOnFailureListener{
                trySend(RegisterLogin.Error(it.message.toString()))
            }
        awaitClose {
            close()
        }
    }

    }
//trySend(RegisterLogin.Loading())
//val result = firebaseAuth.createUserWithEmailAndPassword(adminDetails.email,adminDetails.password)
//    .result
//val userId = result.user?.uid
//
//firestore.collection(ADMIN).document(userId!!).set(adminDetails)
//emit(RegisterLogin.Success(result))