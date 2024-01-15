package com.partyapp_admin.repositories

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.partyapp_admin.data.DataOrException
import com.partyapp_admin.data.Product
import com.partyapp_admin.utils.CATEGORY
import com.partyapp_admin.utils.PRODUCTS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SearchRepoImpl @Inject constructor(
    private val firestore: FirebaseFirestore

) : SearchRepo {
    override suspend fun getSearchItem(query: String): Flow<DataOrException<Product, String, Boolean>> {
        return flow {
            emit(DataOrException.Loading(true))
            try {
                val searchResult = firestore.collection(PRODUCTS)
                    .whereGreaterThanOrEqualTo(CATEGORY, query)
                    .whereLessThanOrEqualTo(CATEGORY, query)
                    .get()
                    .await()
                    .map { queryDocumentSnapshot ->
                        queryDocumentSnapshot.toObject(Product::class.java)
                    }
                emit(DataOrException.Success(searchResult))
                Log.d("searched", searchResult.size.toString())

            } catch (e: Exception) {
                emit(DataOrException.Error(e.toString()))
            }

        }
    }
}