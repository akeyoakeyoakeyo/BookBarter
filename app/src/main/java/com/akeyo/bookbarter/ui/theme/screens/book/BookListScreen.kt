package com.akeyo.bookbarter.ui.theme.screens.book

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import com.akeyo.bookbarter.data.BookRepository
import com.akeyo.bookbarter.models.Book
import com.akeyo.bookbarter.navigation.ROUTE_EDIT_BOOK
import com.akeyo.bookbarter.viewmodel.BookViewModel

@Composable

fun BookListScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var bookRepository = BookViewModel(navController, context)

        val emptyUploadState = remember { mutableStateOf(Book("","","","","")) }
        var emptyUploadsListState = remember { mutableStateListOf<Book>() }

        var uploads = bookRepository.viewUploads(emptyUploadState, emptyUploadsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All uploads",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red)

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
                        BookRepository = BookRepository
                    )
                }
            }
        }
    }
}


@Composable
fun UploadItem(name:String, quantity:String, price:String, imageUrl:String, id:String,
               navController: NavHostController, productRepository:BookViewModel) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = name)
        Text(text = quantity)
        Text(text = price)
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Button(onClick = {
            BookRepository.deleteBook(id)
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