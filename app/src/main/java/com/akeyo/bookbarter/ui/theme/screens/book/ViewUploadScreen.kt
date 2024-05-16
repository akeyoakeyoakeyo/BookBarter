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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.akeyo.akeyoxfirebase.data.bookviewmodel
import com.akeyo.bookbarter.models.Book
import com.akeyo.bookbarter.navigation.ROUTE_EDIT_BOOK
import com.akeyo.bookbarter.navigation.ROUTE_VIEW_UPLOAD

@Composable
fun ViewUploadsScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var productRepository = bookviewmodel(navController, context)


        val emptyUploadState = remember { mutableStateOf(Book("","","","","","","")) }
        var emptyUploadsListState = remember { mutableStateListOf<Book>() }

        var uploads = productRepository.viewUploads(emptyUploadState, emptyUploadsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All uploads",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red)
            navController.navigate(ROUTE_VIEW_UPLOAD)
            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(uploads){
                    UploadItem(
                        title = it.title,
                        author = it.author,
                        description = it.description,
                        imageUrl = it.imageUrl,
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
fun UploadItem(title:String, author:String, description:String, imageUrl:String, id:String,
               navController: NavHostController, productRepository:bookviewmodel) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = title)
        Text(text = author)
        Text(text = description)
//        Text(text = condition)
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Button(onClick = {
            productRepository.deleteBook(id)
        }) {
            Text(text = "Delete")
        }
        Button(onClick = {
            navController.navigate(ROUTE_EDIT_BOOK + "/$id")
        }) {
            Text(text = "Update")
        }
    }
}