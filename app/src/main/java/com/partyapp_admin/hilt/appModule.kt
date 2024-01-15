package com.partyapp_admin.hilt

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.partyapp_admin.repositories.LoginRepo
import com.partyapp_admin.repositories.LoginRepoImpl
import com.partyapp_admin.repositories.OrdersRepo
import com.partyapp_admin.repositories.OrdersRepoImpl
import com.partyapp_admin.repositories.ProductsRepo
import com.partyapp_admin.repositories.ProductsRepoImpl
import com.partyapp_admin.repositories.SearchRepo
import com.partyapp_admin.repositories.SearchRepoImpl
import com.partyapp_admin.repositories.SignupRepo
import com.partyapp_admin.repositories.SignupRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule{
    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesCurrentUser():FirebaseUser?{
        return FirebaseAuth.getInstance().currentUser
    }

    @Provides
    @Singleton
    fun provideFirebaseRealtimeDatabase():FirebaseDatabase {
        return FirebaseDatabase.getInstance("https://partyapp-61b51-default-rtdb.firebaseio.com/")
    }

    @Provides
    @Singleton
    fun providesFirebaseFirestoreInstance() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun providesSignupRepository(firebaseAuth: FirebaseAuth,firebaseFirestore: FirebaseFirestore):SignupRepo{
        return SignupRepoImpl(firebaseAuth,firebaseFirestore)
    }

    @Provides
    @Singleton
    fun providesLoginRepository(firebaseAuth: FirebaseAuth):LoginRepo {
        return LoginRepoImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun providesProductsRepository(firebaseFirestore: FirebaseFirestore):ProductsRepo {
        return ProductsRepoImpl(firebaseFirestore)
    }

    @Provides
    @Singleton
    fun providesOrdersRepository(firebaseDatabase: FirebaseDatabase):OrdersRepo {
        return OrdersRepoImpl(firebaseDatabase)
    }
    @Provides
    @Singleton
    fun providesSearchRepository(firebaseFirestore: FirebaseFirestore):SearchRepo{
        return SearchRepoImpl(firebaseFirestore)
    }
    @Provides
    @Singleton
    fun providesFirebaseStorage():FirebaseStorage{
        return FirebaseStorage.getInstance()
    }

}
