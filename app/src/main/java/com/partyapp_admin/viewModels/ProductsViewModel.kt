package com.partyapp_admin.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.partyapp_admin.data.DataOrException
import com.partyapp_admin.data.Product
import com.partyapp_admin.repositories.ProductsRepo
import com.partyapp_admin.utils.CARBONATED
import com.partyapp_admin.utils.PRODUCTS
import com.partyapp_admin.utils.WHISKY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepo: ProductsRepo,
    private val firestore: FirebaseFirestore
) : ViewModel()  {

    private val _whiskyData = MutableStateFlow<List<Product>>(emptyList())
    val whisky = _whiskyData
    val whiskyLoading = mutableStateOf(false)

    private val _carbonated = MutableStateFlow<List<Product>>(emptyList())
    val carbonated = _carbonated
    val carbonatedLoading = mutableStateOf(false)

    //for itemClick

    var onClick = mutableStateOf(Product())

    init {
        getCarbonated()
        getWhisky()
    }



    fun displayProduct(product: Product){
        onClick.value = product
    }
    private fun getWhisky() = viewModelScope.launch{
        productsRepo.getProductsFromFireStore(WHISKY).collect{
            result -> when(result){
                is DataOrException.Success ->{
                    result.data.let {
                        if(it != null){
                            _whiskyData.value = it
                        }
                    }
                    whiskyLoading.value = false

                }
            is DataOrException.Loading ->{
                whiskyLoading.value = true

            }
            is DataOrException.Error ->{
                whiskyLoading.value = false

            }
            }

        }

    }
    private fun getCarbonated(){
        viewModelScope.launch {
            productsRepo.getProductsFromFireStore(CARBONATED).collect{
                result -> when(result){
                    is DataOrException.Success ->{
                        result.data.let {
                            if(it!= null){
                                _carbonated.value = it
                            }
                        }
                        carbonatedLoading.value = false
                    }
                is DataOrException.Loading ->{
                    carbonatedLoading.value = true

                }
                is DataOrException.Error ->{
                    carbonatedLoading.value = false

                }
                }
            }
        }
    }

    fun updateProductDetails(product: Product){

        firestore.collection(PRODUCTS)

    }

}