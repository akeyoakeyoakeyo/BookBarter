package com.akeyo.bookbarter.ui.theme.screens.book

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.akeyo.akeyoxfirebase.data.bookviewmodel
import com.akeyo.bookbarter.R
import com.akeyo.bookbarter.models.Book
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun EditBooksScreen(navController: NavHostController, id:String) {

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.kawaii),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var context = LocalContext.current
            // Initialize title, author, and price as nullable strings
            var title by remember { mutableStateOf<String?>(null) }
            var author by remember { mutableStateOf<String?>(null) }
            var description by remember { mutableStateOf<String?>(null) }
//            var conditon by remember { mutableStateOf<String?>(null) }

            // Fetch data from Firebase and assign values to title, author, and price
            var currentDataRef = FirebaseDatabase.getInstance().getReference()
                .child("Products/$id")
            currentDataRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var product = snapshot.getValue(Book::class.java)
                    title = product!!.title
                    author = product.author
                    description = product.description
//                    conditon = product.condition
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }
            })

            Text(
                text = "Edit book",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red,
                modifier = Modifier.padding(20.dp),
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )

            var bookTitle by remember { mutableStateOf(TextFieldValue("")) }
            var bookAuthor by remember { mutableStateOf(TextFieldValue("")) }
            var bookDescription by remember { mutableStateOf(TextFieldValue("")) }
            var bookCondition by remember { mutableStateOf(TextFieldValue("")) }
            var imageUrl by remember { mutableStateOf(TextFieldValue("")) }
            var ownerid by remember { mutableStateOf(TextFieldValue("")) }

            OutlinedTextField(
                value = bookTitle,
                onValueChange = { bookTitle = it },
                label = { Text(text = "Book Title *") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = bookAuthor,
                onValueChange = { bookAuthor = it },
                label = { Text(text = "Book Author *") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.height(20.dp))



            OutlinedTextField(
                value = bookDescription,
                onValueChange = { bookDescription = it },
                label = { Text(text = "Book Description *") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )


            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = bookCondition,
                onValueChange = { bookCondition = it },
                label = { Text(text = "Book Condition *") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                //-----------WRITE THE UPDATE LOGIC HERE---------------//
                var BookRepository = bookviewmodel(navController, context)

                BookRepository.updateBook(
                    bookTitle.text.trim(), bookAuthor.text.trim(), bookDescription.text.trim(),
                    bookCondition.text.trim(), imageUrl.text.trim(), id, ownerid.text.trim())


            }) {
                Text(text = "Update")
            }

        }


    }
}


@Preview
@Composable
 fun Editprev() {
     EditBooksScreen(rememberNavController(), id ="")

}