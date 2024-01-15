package com.partyapp_admin.data

data class SignUpState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: String? = ""
)

data class LoginState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: String? = ""
)

sealed class RegisterLogin<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : RegisterLogin<T>(data)
    class Loading<T>(data: T? = null) : RegisterLogin<T>(data)
    class Error<T>(message: String, data: T? = null) : RegisterLogin<T>(data, message)
}

data class AdminDetails(
    val email: String = "",
    val password: String = "",
    val firstname: String = "",
    val secondName: String = "",
    val phoneNumber: String = "",
    val employeeNumber: Int = 0,
)

sealed class DataOrException<T, E : String?, isLoading : Boolean?>
    (val data: List<T>? = null, val error: E? = null, val loading: Boolean? = false) {
    class Success<T>(data: List<T>) : DataOrException<T, String, Boolean>(data)
    class Loading<T>(loading: Boolean) : DataOrException<T, String, Boolean>(loading = loading)
    class Error<T>(error: String) : DataOrException<T, String, Boolean>(error = error)
}

data class Product(
    val name: String = "",
    val category: String = "",
    val price: String = "",
    val desc: String = "",
    val image: String = "",
    val litres: String = "",
    val available: Boolean = false,
    val color: String = "",
    val amount: Int = 0,
    val amountBought: Int = 0
)

data class UpdateDB(
    val updated : Boolean= false,
    val error:Boolean = false,
    val loading: Boolean = false
)

//data class CustomerOrder(
//    val orderPrice: Int = 0,
//    val orderQuantity: Int = 0,
//    val productImagesUrls: String = "",
//    val productPrice: Int = 0,
//    val productTitle: String = "",
//    val totalAmount: Int = 0,
//    val amountBought : Int = 0
//)
data class FinalCustomerOrder(
    var productTitle: String = "",
    var productPrice: Double = 0.00,
    var productImagesUrls: String? = null,
    var orderQuantity: Int = 0,
    var orderPrice: Double = 0.00,
    var totalAmount : Int = 0,
    var amountBought : Int = 0,
    var orderTimestamp: String = "",
    var deliveryDateOne : String = "",
    var deliveryDateTwo : String = "",
)