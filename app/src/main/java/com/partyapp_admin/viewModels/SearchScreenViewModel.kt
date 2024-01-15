package com.partyapp_admin.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.partyapp_admin.data.DataOrException
import com.partyapp_admin.data.Product
import com.partyapp_admin.repositories.SearchRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val searchRepo: SearchRepo
): ViewModel() {

    private val _searchResult = MutableStateFlow<List<Product>>(emptyList())
    val searchResult = _searchResult

    fun getSearchedProduct (query : String) = viewModelScope.launch {
        searchRepo.getSearchItem(query).collect{
            result -> when(result){
                is DataOrException.Success ->{
                    if (result.data != null){
                        _searchResult.value = result.data
                    }
                }
            is DataOrException.Error ->{

            }
            is DataOrException.Loading ->{

            }
            }
        }
    }
}