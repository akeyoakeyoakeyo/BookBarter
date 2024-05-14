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
import com.google.firebase.firestore.FirebaseFirestore

@Composable

fun BookListScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var bookRepository = BookViewModel(BookRepository(FirebaseFirestore))

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

            LazyColumn {
                items(uploads){
                    UploadItem(
                        title = "",
                        author = "",
                        description = "",
                        imageUrl = "",
                        id = "",
                        navController = navController,
                        BookRepository = BookViewModel(BookRepository(FirebaseFirestore))


                    )
                }
            }
        }
    }
}


@Composable
fun UploadItem(title:String, author:String, description:String, imageUrl:String, id:String,
               navController: NavHostController, BookRepository:BookViewModel) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = title)
        Text(text = author)
        Text(text = description)
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