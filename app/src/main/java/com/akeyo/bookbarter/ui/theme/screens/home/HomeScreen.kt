package com.akeyo.bookbarter.ui.theme.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.akeyo.bookbarter.R
import com.akeyo.bookbarter.navigation.ROUTE_LOGIN
import com.akeyo.bookbarter.navigation.ROUTE_REGISTER
import com.akeyo.bookbarter.navigation.ROUTE_USER_PROFILE

@Composable
fun HomeScreen(navController:NavHostController) {
    Box (modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.kawaii),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Column (horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()

        )   {

            Text(text = "WELCOME TO \n    BOOKBARTER",
                fontSize = 35.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(50.dp))



            Text(text = "A good book for every one",
                fontSize = 25.sp,
                fontFamily = FontFamily.Serif,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(40.dp))

            Text(text = "A book exchange platform that enables you to connect with thousands of other readers like you to exchange books for free! Enabling an affordable sustainable reading culture. ",
                fontSize = 18.sp,
                fontFamily = FontFamily.Serif,
                color = Color.White,

                )

            Spacer(modifier = Modifier.height(150.dp))

            Button(onClick = { navController.navigate(ROUTE_LOGIN) },
                colors = ButtonDefaults.buttonColors(Color.White),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .width(350.dp)

            ) {
                Text(text = "Sign in",
                    fontSize = 28.sp,
                    color = Color.Magenta,
                    modifier = Modifier

                )

            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(text = "Create an account",
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
fun Homeprev() {
    HomeScreen(rememberNavController())

}