package com.akeyo.bookbarter.data

import com.akeyo.bookbarter.models.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestoreException
import dagger.hilt.android.HiltAndroidApp

//@HiltAndroidApp
class BookRepository (private val firestore: FirebaseFirestore){

    //Functions to interact with the database and API
    /**
     * Saves a book to the Firestore database.
     *
     * @param book The book to save.
     * @throws FirestoreException if an error occurs while saving the book.
     */

    suspend fun saveBook(book: Book){
     try {
         //Get a reference to the "books" collection in Firestore
         val booksCollection = firestore.collection("books")

         //Add the book to the "books" collection

         booksCollection.add(book).await() } catch (e: Exception){
             //Handle any errors
             e.printStackTrace()
            //Optionally, you can throw the exception to handle it in the ViewModel
                throw  e

         }
    }

}