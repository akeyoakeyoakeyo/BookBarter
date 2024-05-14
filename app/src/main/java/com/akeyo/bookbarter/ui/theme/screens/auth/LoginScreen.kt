package com.akeyo.bookbarter.ui.theme.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.akeyo.bookbarter.R
import com.akeyo.bookbarter.navigation.ROUTE_REGISTER
import com.akeyo.bookbarter.navigation.ROUTE_USER_PROFILE
import com.akeyo.bookbarter.viewmodel.AuthViewModel
import dagger.hilt.android.HiltAndroidApp

@Composable
fun LoginScreen(navController: NavHostController, authViewModel: AuthViewModel = ViewModel())  {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }




    Box (modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.kawaii),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Row (horizontalArrangement = Arrangement.Absolute.Left){

            Image(painter = painterResource(id = R.drawable.back),
                contentDescription = "back",
                Modifier.width(40.dp))
//            modifier = Modifier.clickable {  }

            Spacer(modifier = Modifier.width(20.dp))
            Spacer(modifier = Modifier.height(70.dp))



            Text(text = "Skip",
                fontSize = 18.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.White,
                textAlign = TextAlign.Left,
//                modifier = Modifier.clickable {  }


            )

        }

        Column (horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()

        )   {





            Spacer(modifier = Modifier.height(70.dp))





            Text(text = "Welcome Back",
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "Login to your account",
                fontSize = 18.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(40.dp))


            OutlinedTextField(value = username,
                onValueChange = {username=it},
                colors = TextFieldDefaults.colors(Color.White),
                label = {
                    Text(text = "User Name:",
                        color = Color.Black,
                        fontFamily = FontFamily.Default,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold


                    )},
                modifier = Modifier
                    .width(350.dp)
                    .padding(vertical = 10.dp),
//            keyboardActions = KeyboardOptions(imeAction = ImeAction.Next)
            )


            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                colors = TextFieldDefaults.colors(Color.White),
                label = {
                    Text(
                        text = "Password:",
                        color = Color.Black,
                        fontFamily = FontFamily.Default,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier
                    .width(350.dp)
                    .padding(vertical = 10.dp),

//            keyboardActions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Row {
//                Checkbox(checked = false, onCheckedChange = )

                Spacer(modifier = Modifier.width(20.dp))
                Spacer(modifier = Modifier.height(70.dp))



                Text(text = "Remember me",
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White,
                    textAlign = TextAlign.Left,
//                modifier = Modifier.clickable {  }


                )
            }

            Button(onClick = {
                             authViewModel.login(email = "", pass = "")
//                navController.navigate(ROUTE_USER_PROFILE)
                             },
                colors = ButtonDefaults.buttonColors(Color.White),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .width(350.dp)
            ) {
                Text(text = "Login",
                    fontSize = 28.sp,
                    color = Color.Magenta,
                    modifier = Modifier
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(text = "Don't have an account?   Sign up",
                fontSize = 15.sp,
                fontFamily = FontFamily.Serif,
                color = Color.Black,
                modifier = Modifier.clickable { navController.navigate(ROUTE_REGISTER) }
            )
            Spacer(modifier = Modifier.height(40.dp))

        }
    }


}


@Preview
@Composable
fun Loginprev() {

    LoginScreen(rememberNavController())

}