package com.akeyo.bookbarter.viewmodel

import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akeyo.bookbarter.data.BookRepository
import com.akeyo.bookbarter.models.Book
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class BookViewModel (private val repository: BookRepository):ViewModel(){

    //Functions to fetch and manipulate book data
    fun saveBook(book: Book) {
//        var id = System.currentTimeMillis().toString()
//        var productData = Product(productName, productQuantity, productPrice, id)
//        var productRef = FirebaseDatabase.getInstance().getReference()
//            .child("Products/$id")
//        progress.show()
//        productRef.setValue(productData).addOnCompleteListener {
//            progress.dismiss()
//            if (it.isSuccessful) {
//                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
        viewModelScope.launch {
            repository.saveBook(book)
        }


    }

    fun saveBookWithImage (book: Book, imageData: ByteArray){
        viewModelScope.launch (Dispatchers.IO){
            //Save the image file first and get its URL

            val imageUrl = saveImageToFile (imageData)

            //Update the book's image URL

            val bookwithImage = book.copy(imageUrl = imageUrl)

            //Use the repository to save the book with the updated image URL

            repository.saveBook(bookwithImage)


        }
    }

    private suspend fun
            saveImageToFile(imageData: ByteArray): String {
        try {


            //Implement logic to save image data to a file and return its URL
            //For example, you can use a library like Glide or Picasso to load the image data
            //into a file and return the file's URL
            //This is just a placeholder implementation


            //Create a temporary file
            val imageFile = File.createTempFile("book_image", ".jpg")

            //Write image data to the file
            imageFile.outputStream().use { outputStream ->
                outputStream.write(imageData)
            }


//        imageFile.writeBytes(imageData)

            //Return the absolute path of the image file
            return
            imageFile.absolutePath
        } catch (e: Exception){//Handle any errors
            e.printStackTrace()
            return ""
            //Return an empty string if there's an error

        }

    }

}