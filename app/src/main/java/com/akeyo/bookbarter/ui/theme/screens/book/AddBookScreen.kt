package com.akeyo.bookbarter.ui.theme.screens.book

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
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
import com.akeyo.bookbarter.models.Book
import com.akeyo.bookbarter.navigation.ROUTE_BOOK_LIST
import com.akeyo.bookbarter.navigation.ROUTE_EDIT_BOOK
import com.akeyo.bookbarter.navigation.ROUTE_USER_PROFILE
import com.akeyo.bookbarter.navigation.ROUTE_VIEW_UPLOAD


@Composable
fun AddBooksScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context = LocalContext.current
        Text(
            text = "Add book",
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
//        var filePath by remember { mutableStateOf(Uri()) }

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
            //-----------WRITE THE SAVE LOGIC HERE---------------//
            var productRepository = bookviewmodel(navController,context)
//            productRepository.saveBookWithImage(bookTitle.text.trim(),bookAuthor.text.trim(), bookDescription.text.trim(), ownerid.text.trim(), bookCondition.text.trim(),
//              filePath )
            navController.navigate(ROUTE_BOOK_LIST)


        }) {
            Text(text = "Save")
        }
        Spacer(modifier = Modifier.height(20.dp))

        //---------------------IMAGE PICKER START-----------------------------------//

        ImagePicker(Modifier,context, navController, bookTitle.text.trim(), bookAuthor.text.trim(), bookCondition.text.trim())

        //---------------------IMAGE PICKER END-----------------------------------//

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Button(onClick = { navController.navigate(ROUTE_EDIT_BOOK)}) {
                Text(text = "Edit \n Book", color = Color.Cyan)
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(onClick = { navController.navigate(ROUTE_VIEW_UPLOAD)}) {
                Text(text = "View \n Uploads", color = Color.Cyan)
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(onClick = { navController.navigate(ROUTE_BOOK_LIST)}) {
                Text(text = "Book \n List", color = Color.Cyan)
            }


        }


    }
}

@Composable
fun ImagePicker(modifier: Modifier = Modifier, context: Context, navController: NavHostController, title:String, author:String, description:String) {
    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    Column(modifier = modifier,) {
        if (hasImage && imageUri != null) {
            val bitmap = MediaStore.Images.Media.
            getBitmap(context.contentResolver,imageUri)
            Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Selected image")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp), horizontalAlignment = Alignment.CenterHorizontally,) {
            Button(
                onClick = {
                    imagePicker.launch("image/*")
                },
            ) {
                Text(
                    text = "Select Image"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                //-----------WRITE THE UPLOAD LOGIC HERE---------------//
                var productRepository = bookviewmodel(navController,context)
                val imageUrl = ""
                val ownerid = ""
                val condition = ""
                productRepository.saveBook(title, author, description, imageUrl , ownerid, condition  )


            }) {
                Text(text = "Upload")
            }
        }
    }
}


@Preview
@Composable
fun Addprev() {
    AddBooksScreen(rememberNavController())

}