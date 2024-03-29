package com.partyapp_admin.screens

import android.util.Patterns
import android.widget.Toast
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.partyapp_admin.R
import com.partyapp_admin.data.AdminDetails
import com.partyapp_admin.navigation.AuthGraph
import com.partyapp_admin.navigation.RootGraph
import com.partyapp_admin.viewModels.SignUpViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    signUpViewModel: SignUpViewModel

) {

    val status = signUpViewModel.signUpStatus.collectAsState(initial = null)

    val context = LocalContext.current

    var firstName by rememberSaveable {
        mutableStateOf("")
    }
    var surname by rememberSaveable {
        mutableStateOf("")
    }
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var empNo by rememberSaveable {
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
        mutableStateOf(true)
    }
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .verticalScroll(state = rememberScrollState(), enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            painter = painterResource(id = R.drawable.baseline_wine_bar_24),
            contentDescription = null
        )

        Text(
            text = stringResource(id = R.string.register),
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp,
            modifier = modifier.padding(20.dp)
        )

        OutlinedText(
            value = firstName,
            onValueChanged = { firstName = it },
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
            value = empNo,
            onValueChanged = { empNo = it },
            visualTransformation = VisualTransformation.None,
            icon = R.drawable.baseline_blur_circular_24,
            label = R.string.emp_no,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedText(
            value = telephone,
            onValueChanged = { telephone = it },
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
            onClick = {
                val check = firstNameCheck(firstName) && secondNameCheck(surname) &&
                        emailCheck(email) && telephoneCheck(telephone)
                        && checkPassword(password, confirmPassword)
                if (!check) {
                    Toast.makeText(context, "Check if all fields are correct", Toast.LENGTH_LONG)
                        .show()
                } else if (!checkPassword(password, confirmPassword)) {
                    Toast.makeText(context, "Passwords do not Match!!", Toast.LENGTH_LONG).show()
                } else {
                    scope.launch(Dispatchers.Main) {
                        val adminDetails = AdminDetails(
                            email = email,
                            password = password,
                            firstname = firstName,
                            secondName = surname,
                            phoneNumber = telephone,
                            employeeNumber = empNo.toInt()
                        )

                        signUpViewModel.registerAdmin(adminDetails)
                    }


                }

            },
            enabled = isAgreed,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .height(50.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                disabledContainerColor = MaterialTheme.colorScheme.tertiary,

                )
        ) {
            Text(text = stringResource(id = R.string.register),
                color = MaterialTheme.colorScheme.surface)
        }



        if (status.value?.isLoading == true) {
            CircularProgressIndicator(
                modifier = modifier.height(15.dp),
                color = MaterialTheme.colorScheme.surfaceTint
            )
        }
        Spacer(modifier = modifier.height(13.dp))

        Row {
            Text(
                text = stringResource(id = R.string.have_account),
                modifier = modifier.padding(top = 15.dp)
            )

            TextButton(onClick = {
                navHostController.navigate(AuthGraph.LoginPage.route)

            }) {
                Text(
                    text = stringResource(id = R.string.login),
                    color = MaterialTheme.colorScheme.surface

                    )
            }

        }

    }

    LaunchedEffect(key1 = status.value?.isError) {
        scope.launch(Dispatchers.Main) {
            if (status.value?.isError?.isNotEmpty() == true) {
                Toast.makeText(context, "${status.value!!.isError}", Toast.LENGTH_SHORT).show()

            }
        }
    }
    LaunchedEffect(key1 = status.value?.isSuccess) {
        if (status.value?.isSuccess == true) {
            Toast.makeText(context, "successfully registered", Toast.LENGTH_LONG).show()
            navHostController.navigate(RootGraph.Content.route) {
                popUpTo(RootGraph.Content.route) {
                    inclusive = true
                }
            }

        }
    }


}

fun firstNameCheck(firstName: String): Boolean {


    if (firstName.any {
            it.isDigit()
        }) {
        return false
    } else if (firstName.length < 3 || firstName.isBlank()) {
        return false
    }

    return true
}

//fun adminNoCheck(number: String): Boolean {
//
//    return !number.any {
//        it.isLetter()
//    }
//}

fun checkPassword(password: String, checkPassword: String): Boolean {

    return password == checkPassword
}

fun secondNameCheck(surname: String): Boolean {
    if (surname.any {
            it.isDigit()
        }) {
        return false
    } else if (surname.length < 3 || surname.isBlank()) {
        return false
    }

    return true
}

fun emailCheck(email: String): Boolean {
    if (email.isBlank()) {
        return false
    }
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun telephoneCheck(phone: String): Boolean {
    if (phone.isBlank()) {
        return false
    }
    if (phone.any {
            it.isLetter()
        }) {
        return false
    }
    return !(!Patterns.PHONE.matcher(phone).matches() || phone.isBlank())

}

//@Composable
//@Preview(showSystemUi = true, showBackground = true)
//fun ShowPrev() {
//    RegisterScreen(navHostController = null)
//}
