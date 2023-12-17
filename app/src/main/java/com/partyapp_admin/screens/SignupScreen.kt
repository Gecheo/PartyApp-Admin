package com.partyapp_admin.screens

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.partyapp_admin.R

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,

){

    var firstName by rememberSaveable {
        mutableStateOf("")
    }
    var surname by rememberSaveable {
        mutableStateOf("")
    }
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var telephone by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var confirmPassword by rememberSaveable {
        mutableStateOf("")
    }
    val isAgreed by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .verticalScroll(state = rememberScrollState(), enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(10.dp))

        Icon(painter = painterResource(id = R.drawable.baseline_wine_bar_24),
            contentDescription = null)

        Text(
            text = stringResource(id = R.string.register),
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp,
            modifier = modifier.padding(20.dp)
        )

        OutlinedText(
            value = firstName,
            onValueChanged = {firstName = it },
            visualTransformation = VisualTransformation.None,
            icon = R.drawable.outline_person_24,
            label = R.string.First_name,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        OutlinedText(
            value = surname,
            onValueChanged = { surname = it },
            visualTransformation = VisualTransformation.None,
            icon = R.drawable.outline_person_24,
            label = R.string.Second_name,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        OutlinedText(
            value = email,
            onValueChanged = { email = it },
            visualTransformation = VisualTransformation.None,
            icon = R.drawable.outline_email_24,
            label = R.string.email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        OutlinedText(
            value = telephone,
            onValueChanged = {telephone = it },
            visualTransformation = VisualTransformation.None,
            icon = R.drawable.outline_local_phone_24,
            label = R.string.telephone,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        OutlinedText(
            value = password,
            onValueChanged = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            icon = R.drawable.outline_lock_24,
            label = R.string.password,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        OutlinedText(
            value = confirmPassword,
            onValueChanged = { confirmPassword = it },
            visualTransformation = PasswordVisualTransformation(),
            icon = R.drawable.outline_lock_24,
            label = R.string.confirm_password,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        OutlinedButton(
            onClick = {},
            enabled = isAgreed,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .height(50.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                disabledContainerColor = colorResource(id = R.color.black),
                contentColor = colorResource(id = R.color.white)
            )
        ) {
            Text(text = stringResource(id = R.string.register))
        }


//
//        if (state.value?.isLoading == true){
//            CircularProgressIndicator(
//                modifier = modifier.height(15.dp),
//                color = colorResource(id = R.color.very_light_green)
//            )
//        }
        Spacer(modifier = modifier.height(13.dp))

        Row {
            Text(text = stringResource(id = R.string.have_account),
                color = Color.White,
                modifier = modifier.padding(top = 15.dp))

            TextButton(onClick = {

            }) {
                Text(text = stringResource(id = R.string.login),
                    color = Color.White)
            }

        }

    }

//    LaunchedEffect(key1 = state.value?.isError ){
//        scope.launch(Dispatchers.Main) {
//            if (state.value?.isError?.isNotEmpty() == true){
//                val error = state.value?.isError
//                Toast.makeText(context,"$error",Toast.LENGTH_SHORT).show()
//
//            }
//        }
//    }
//    LaunchedEffect(key1 = state.value?.isSuccess) {
//        if (state.value?.isSuccess?.isNotEmpty() == true) {
//            val successful = state.value?.isSuccess
//            Toast.makeText(context, successful, Toast.LENGTH_LONG).show()
//            navHostController.navigate(Graph.HOME)
//
//        }
//    }

}
fun firstNameCheck(firstName:String):Boolean {


    if(firstName.any {
            it.isDigit() }){
        return false
    }
    else if (firstName.length < 3 || firstName.isBlank()){
        return false
    }

    return true
}
fun secondNameCheck(surname:String):Boolean{
    if(surname.any {
            it.isDigit()
        }){
        return false
    }
    else if (surname.length < 3 || surname.isBlank()){
        return false
    }

    return true
}

fun emailCheck(email:String):Boolean{
    if (email.isBlank()){
        return false
    }
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
fun telephoneCheck(phone:String): Boolean{
    if (phone.isBlank()){
        return false
    }
    if (phone.any {
            it.isLetter() }){
        return false
    }
    return !(!Patterns.PHONE.matcher(phone).matches() || phone.isBlank())

}


