package com.partyapp_admin.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.partyapp_admin.R
import com.partyapp_admin.navigation.DetailsScreen
import com.partyapp_admin.navigation.RootGraph


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
){

    var userEmail by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState(), enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Spacer(modifier = modifier.height(10.dp))
        Icon(painter = painterResource(id = R.drawable.baseline_wine_bar_24),
            contentDescription = null,)

        Text(
            text = stringResource(id = R.string.login),
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,

            modifier = modifier.padding(20.dp)
        )
        Spacer(modifier = modifier.height(100.dp))

        OutlinedText(
            value = userEmail,
            onValueChanged = {userEmail= it},
            visualTransformation = VisualTransformation.None,
            icon = R.drawable.outline_person_24,
            label = R.string.email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )



        OutlinedText(
            value = password,
            onValueChanged = {password =  it},
            visualTransformation = PasswordVisualTransformation(),
            icon = R.drawable.outline_lock_24,
            label = R.string.password,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )


        Spacer(modifier = modifier.height(10.dp))
        OutlinedButton(
            onClick = {
                navController.navigate(RootGraph.Content.route)
            },
            enabled = true,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .height(50.dp),
            colors = ButtonDefaults.outlinedButtonColors(

            )
        ) {
            Text(text = "Navigate to SignUp")
        }

        Row {
            Text(text = stringResource(id = R.string.email),
                color = Color.White,
                modifier = modifier.padding(top = 15.dp))

            TextButton(onClick = {

            }) {

            }

        }

    }


}

@Composable
fun OutlinedText(
    value:String,
    onValueChanged:(String)->Unit,
    visualTransformation: VisualTransformation,
    icon:Int,
    label:Int,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions

){
    val containerColor = colorResource(id =R.color.black )
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        visualTransformation = visualTransformation,
        leadingIcon = { Icon(painter = painterResource(id = icon), contentDescription = null)},
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 18.dp, top = 9.dp, bottom = 9.dp),
        shape = CutCornerShape(topStart = 10.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 10.dp),
        label = { Text(text = stringResource(id = label))},
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,

        ),
        keyboardOptions = keyboardOptions


    )
}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun LoginPrev(){
//    LoginScreen( modifier = Modifier)
//
//}
