package com.partyapp_admin.repositories

import com.partyapp_admin.data.DataOrException
import com.partyapp_admin.data.Product
import kotlinx.coroutines.flow.Flow

interface SearchRepo {
    suspend fun getSearchItem(query: String): Flow<DataOrException<Product,String,Boolean>>
}