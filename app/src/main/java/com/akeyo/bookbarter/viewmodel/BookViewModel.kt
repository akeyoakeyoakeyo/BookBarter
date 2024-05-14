package com.akeyo.bookbarter.viewmodel

import android.net.Uri
import android.app.ProgressDialog
import android.widget.Toast
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akeyo.bookbarter.data.BookRepository
import com.akeyo.bookbarter.models.Book
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import kotlin.coroutines.jvm.internal.CompletedContinuation.context

@HiltViewModel
class BookViewModel @Inject constructor(private val repository: BookRepository):ViewModel(){

    //Functions to fetch and manipulate book data
    fun saveBook(book: Book) {
//        var id = System.currentTimeMillis().toString()
//        var productData = Product(productName, productQuantity, productPrice, id)
//        var productRef = FirebaseDatabase.getInstance().getReference()
//            .child("Products/$id")

        viewModelScope.launch {
            repository.saveBook(book)
        }
        progress.show()
        productRef.setValue(productData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }

    fun viewUploads(upload: MutableState<Book>, uploads: SnapshotStateList<Book>): SnapshotStateList<Upload> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Uploads")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                uploads.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Book::class.java)
                    upload.value = value!!
                    uploads.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return uploads
    }

    fun deleteBook(id: String) {
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("Products/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Book deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateBook(name: String, quantity: String, price: String, id: String) {
        var updateRef = FirebaseDatabase.getInstance().getReference()
            .child("Products/$id")
        progress.show()
        var updateData = Book(name, quantity, price, id)
        updateRef.setValue(updateData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
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