package com.akeyo.akeyoxfirebase.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.akeyo.bookbarter.models.Book
import com.akeyo.bookbarter.navigation.ROUTE_LOGIN
import com.akeyo.bookbarter.viewmodel.AuthViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage


class bookviewmodel(var navController: NavHostController, var context: Context) {
    var authRepository: AuthViewModel
    var progress: ProgressDialog

    init {
        authRepository = AuthViewModel(navController, context)
        if (!authRepository.isloggedin()) {
            navController.navigate(ROUTE_LOGIN)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }


    fun saveBook(bookTitle: String, bookAuthor: String, bookDescription: String, imageUrl: String, ownerid: String, bookCondition:String ) {
        var id = System.currentTimeMillis().toString()
        var productData = Book(bookTitle, bookAuthor, bookDescription,bookCondition, imageUrl,ownerid, id, )
        var productRef = FirebaseDatabase.getInstance().getReference()
            .child("Products/$id")
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

    fun viewBooks(
        product: MutableState<Book>,
        products: SnapshotStateList<Book>
    ): SnapshotStateList<Book> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Products")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                products.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Book::class.java)
                    product.value = value!!
                    products.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return products
    }

    fun deleteBook(id: String) {
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("Products/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Product deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateBook(title: String, author: String, description: String, imageUrl:String, ownerid:String,condition:String, id: String) {
        var updateRef = FirebaseDatabase.getInstance().getReference()
            .child("Products/$id")
        progress.show()
        var updateData = Book(title , author, description, imageUrl, ownerid, condition, id)
        updateRef.setValue(updateData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

//    fun saveBookWithImage(bookTitle:String, bookAuthor: String, bookDescription: String, ownerid: String, bookCondition:String, filePath: Uri){
//        var id = System.currentTimeMillis().toString()
//        var storageReference = FirebaseStorage.getInstance().getReference().child("Uploads/$id")
//        progress.show()
//
//        storageReference.putFile(filePath).addOnCompleteListener{
//            progress.dismiss()
//            if (it.isSuccessful){
//                // Proceed to store other data into the db
//                storageReference.downloadUrl.addOnSuccessListener {
//                    var imageUrl = it.toString()
//                    var houseData = Book(bookTitle,bookAuthor, bookCondition,
//                        bookDescription,imageUrl,id, ownerid)
//                    var dbRef = FirebaseDatabase.getInstance()
//                        .getReference().child("Uploads/$id")
//                    dbRef.setValue(houseData)
//                    Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT).show()
//                }
//            }else{
//                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
//            }
//        }
//    }


    fun viewUploads(upload:MutableState<Book>, uploads:SnapshotStateList<Book>): SnapshotStateList<Book> {
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


}