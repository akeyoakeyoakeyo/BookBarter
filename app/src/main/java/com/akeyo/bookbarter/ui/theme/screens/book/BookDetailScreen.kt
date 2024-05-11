package com.akeyo.bookbarter.ui.theme.screens.book

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.akeyo.bookbarter.models.Book
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.akeyo.bookbarter.R

@Composable
fun BookDetailScreen(navController: NavHostController, book: Book) {

    Box (modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.kawaii),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Book Details",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Display book details
            Text(text = "Title: ${book.title}")
            Text(text = "Author: ${book.author}")
            Text(text = "Condition: ${book.condition}")

            Spacer(modifier = Modifier.height(24.dp))

            // Button to navigate back
            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Go Back")
            }
        }


    }
}

@Preview
@Composable
fun Detailprev() {
    val sampleBook = Book(
        id = "1",
        title = "Sample Book Title",
        author = "Sample Author",
        condition = "Sample Condition",
        imageUrl = "Sample url",
        ownerId = "1"
        // You can add other properties as well
    )

    BookDetailScreen(rememberNavController(), book = sampleBook)
}