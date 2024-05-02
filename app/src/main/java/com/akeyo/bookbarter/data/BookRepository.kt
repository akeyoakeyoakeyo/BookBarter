package com.akeyo.bookbarter.data

import com.akeyo.bookbarter.models.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class BookRepository (private val firestore: FirebaseFirestore){

    //Functions to interact with the database and API

    suspend fun saveBook(book: Book){
     try {
         //Get a reference to the "books" collection in Firestore
         val booksCollection = firestore.collection("books")

         //Add the book to the "books" collection

         booksCollection.add(book).await() } catch (e: Exception){
             //Handle any errors
             e.printStackTrace()
            //Optionally, you can throw the exception to handle it in the ViewModel
                throw e

         }
    }

}