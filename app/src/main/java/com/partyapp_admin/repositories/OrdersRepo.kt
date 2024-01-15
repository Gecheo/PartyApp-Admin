package com.partyapp_admin.repositories


import com.partyapp_admin.data.DataOrException
import com.partyapp_admin.data.FinalCustomerOrder
import kotlinx.coroutines.flow.Flow

interface OrdersRepo{
 suspend fun getOrders(userId : String) : Flow<DataOrException<FinalCustomerOrder,String,Boolean>>

}

