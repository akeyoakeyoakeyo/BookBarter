package com.akeyo.bookbarter.ui.theme.screens.book

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.akeyo.akeyoxfirebase.data.bookviewmodel
import com.akeyo.bookbarter.models.Book
import com.akeyo.bookbarter.navigation.ROUTE_EDIT_BOOK


@Composable

fun BookListScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var productRepository = bookviewmodel(navController, context)

        val emptyUploadState = remember { mutableStateOf(Book("","","","","","", "")) }
        var emptyUploadsListState = remember { mutableStateListOf<Book>() }

        var uploads = productRepository.viewUploads(emptyUploadState, emptyUploadsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All books",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(uploads){
                    ProductItem(
                        title = it.title,
                        author = it.author,
                        description = it.description,
                        id = it.id,
                        navController = navController,
                        productRepository = productRepository
                    )
                }
            }
        }
    }

}

@Composable
fun ProductItem(title:String, author:String, description:String, id:String,
                navController:NavHostController, productRepository:bookviewmodel) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = title)
        Text(text = author)
        Text(text = description)
        Button(onClick = {
            productRepository.deleteBook(id)
        }) {
            Text(text = "Delete")
        }
        Button(onClick = {
            navController.navigate(ROUTE_EDIT_BOOK+"/$id")
        }) {
            Text(text = "Update")
        }
    }

}

@Preview
@Composable
fun Listprev() {
    BookListScreen(rememberNavController())

}