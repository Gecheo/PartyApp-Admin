package com.partyapp_admin.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.partyapp_admin.data.FinalCustomerOrder
import com.partyapp_admin.utils.ORDERS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val currentUser: FirebaseUser?
) : ViewModel() {

    private val _orders = MutableStateFlow<List<FinalCustomerOrder>>(emptyList())
    val orders = _orders

    val user = currentUser?.uid

    init {
        getOrders()
    }


    private fun getOrders() = viewModelScope.launch {
        if (currentUser != null) {


            firebaseDatabase.reference.child(ORDERS)
                .child("sUpMqPDCAVh4mpu3gv4qRJjBnl73")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val orderList = mutableListOf<FinalCustomerOrder>()

                        for (snap in snapshot.children) {
                            val item = snap.getValue(FinalCustomerOrder::class.java)
                            if (item != null) {
                                orderList.add(item)
                            }
                            _orders.value = orderList

                            Log.d("list", orderList.forEach {
                                it.orderPrice .toString()
                            }.toString())

                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.d("error", error.toString())
                    }

                })
        }


    }
}