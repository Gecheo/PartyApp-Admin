package com.partyapp_admin.viewModels

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.partyapp_admin.data.Product
import com.partyapp_admin.data.UpdateDB
import com.partyapp_admin.utils.PRODUCTS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class UpdateProductViewModel @Inject constructor(
    private val firebaseStorage: FirebaseStorage,
    private val firebaseFirestore: FirebaseFirestore
) : ViewModel() {

    private val _uploadStatus = mutableStateOf(UpdateDB())
    val uploadStatus = _uploadStatus

    private var _deleteStatus = ""
    val deleteStatus = _deleteStatus

    private var _updateStatus = ""
    val updateStatus = _updateStatus

    fun uploadProduct(image: Uri, product: Product) {

        var storageRef = firebaseStorage.reference.child("Alcohol")
        storageRef = storageRef.child(System.currentTimeMillis().toString())

        storageRef.putFile(image).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                storageRef.downloadUrl.addOnSuccessListener { uri ->
                    val item = Product(
                        name = product.name,
                        category = product.category,
                        price = product.price,
                        desc = product.desc,
                        image = uri.toString(),
                        litres = product.litres,
                        available = product.available,
                        color = product.color,
                        amount = product.amount,
                        amountBought = product.amountBought
                    )
                    _uploadStatus.value = UpdateDB(loading = true)
                    firebaseFirestore.collection("products")
                        .add(item)
                        .addOnCompleteListener { firestoreTask ->

                            if (firestoreTask.isSuccessful) {
                                _uploadStatus.value = UpdateDB(updated = true)
                                _uploadStatus.value = UpdateDB(updated = false)
                            } else {
                                _uploadStatus.value = UpdateDB(error = true)
                                _uploadStatus.value = UpdateDB(updated = false)
                            }
                        }


                }
            }
        }

    }

    suspend fun deleteProduct(name: String) {

        val documentId = firebaseFirestore.collection(PRODUCTS)
            .whereGreaterThanOrEqualTo("name", name)
            .whereLessThanOrEqualTo("name", name)
            .get()
            .await()
            .documents
            /*.forEach { documentSnapshot ->
                val provide = documentSnapshot.toObject(Product::class.java)
                if (provide?.name == name){
                    documentId = documentSnapshot.id.
                    Log.d("id", documentId)
                }
                Log.d("id", documentSnapshot.id)
            }*/


         firebaseFirestore.collection("products")
            .document(documentId[0].id)
            .delete()
            .addOnCompleteListener { task ->
                _deleteStatus = if (task.isSuccessful) {
                    "deleted Successfully"
                } else {
                    "Error deleting document"
                }
            }


    }

    suspend fun updateProduct(name: String,product: Product){
        val documents = firebaseFirestore.collection(PRODUCTS)
            .whereGreaterThanOrEqualTo("name", name)
            .whereLessThanOrEqualTo("name", name)
            .get()
            .await()
            .documents

        val update = HashMap<Any,Any>()

        update["name"] = product.name
        update["desc"] = product.desc
        update["amount"] = product.amount
        update["available"] = product.available

       val documentId = firebaseFirestore.collection(PRODUCTS)
            .document(documents[0].id)
        documentId.set(update, SetOptions.merge())
            .addOnCompleteListener{
                task ->
                _updateStatus = if (task.isSuccessful){
                    "Updated Successfully"
                } else{
                    "Error updating"
                }
            }
    }

}

