package com.partyapp_admin.repositories

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.partyapp_admin.data.DataOrException
import com.partyapp_admin.data.FinalCustomerOrder
import com.partyapp_admin.utils.ORDERS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OrdersRepoImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
) : OrdersRepo {
    override suspend fun getOrders(
        userId: String,

        ): Flow<DataOrException<FinalCustomerOrder, String, Boolean>> {
        return flow {

            emit(DataOrException.Loading(loading = true))
            val tempList = mutableListOf<FinalCustomerOrder>()

            firebaseDatabase.reference
                .child(ORDERS)
                .child(userId)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {

                        for (snap in snapshot.children) {
                            val item = snap.getValue(FinalCustomerOrder::class.java)
                            if (item != null) {
                                tempList.add(item)
                                Log.d("order list", item.productTitle)
                            }

                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })

            emit(DataOrException.Success(tempList))


        }
            .catch {
                emit(DataOrException.Error(it.message.toString()))
            }
    }
}