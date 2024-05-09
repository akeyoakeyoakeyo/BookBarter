package com.akeyo.bookbarter.ui.theme.screens.auth

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.akeyo.bookbarter.R
import com.akeyo.bookbarter.navigation.ROUTE_LOGIN
import com.akeyo.bookbarter.navigation.ROUTE_USER_PROFILE

@Composable
fun RegisterScreen(navController:NavHostController) {

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



            Text(text = "Registration",
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "Create your new account",
                fontSize = 18.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(40.dp))


            OutlinedTextField(value = username,
                onValueChange = {username=it},
                colors = TextFieldDefaults.colors(Color.White),
                label = {
                    Text(text = "User Name/ Mail",
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

            OutlinedTextField(value = email,
                onValueChange = {email=it},
                colors = TextFieldDefaults.colors(Color.White),
                label = {Text(text = "user@mail.com",
                    color = Color.Black,
                    fontFamily = FontFamily.Default,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )},
                modifier = Modifier
                    .width(350.dp)
                    .padding(vertical = 10.dp),
            keyboardActions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            OutlinedTextField(value = password,
                onValueChange = {password=it},
                colors = TextFieldDefaults.colors(Color.White),
                label = {Text(text = "Password",
                    color = Color.Black,
                    fontFamily = FontFamily.Default,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )},
                modifier = Modifier
                    .width(350.dp)
                    .padding(vertical = 10.dp),
            keyboardActions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            Spacer(modifier = Modifier.height(60.dp))

            Button(onClick = { navController.navigate(ROUTE_USER_PROFILE) },
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

            LineBreak


        }
    }

}




@Preview
@Composable
fun Registerprev() {

    RegisterScreen(rememberNavController())

}