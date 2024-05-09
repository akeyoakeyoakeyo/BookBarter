package com.akeyo.bookbarter.models

data class Book(
    val id:String,
    val title:String,
    val author:String,
//    val description:String,
    val imageUrl:String,
    val ownerId:String,
    val condition :String

)
