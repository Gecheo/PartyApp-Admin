package com.partyapp_admin.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.partyapp_admin.data.DataOrException
import com.partyapp_admin.data.Product
import com.partyapp_admin.utils.PRODUCTS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductsRepoImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ProductsRepo {
    override suspend fun  getProductsFromFireStore(product : String): Flow<DataOrException<Product,String,Boolean>> {
        return flow {
            emit(DataOrException.Loading(loading = true))
            try {
                val result = firestore.collection(PRODUCTS)
                    .whereGreaterThanOrEqualTo("category",product)
                    .whereLessThanOrEqualTo("category",product)
                    .get()
                    .await()
                    .map {
                            queryDocumentSnapshot ->  queryDocumentSnapshot.toObject(Product::class.java)
                    }
                emit(DataOrException.Success(result))
            }
            catch (e:Exception){
                emit(DataOrException.Error(error = e.message.toString()))
            }
            }


    }
}