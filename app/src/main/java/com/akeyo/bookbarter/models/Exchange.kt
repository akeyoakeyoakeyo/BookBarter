package com.akeyo.bookbarter.models

data class Exchange(
    val id:String,
    val bookid:String,
    val ownerid:String,
    val recipientid:String,
    val status: ExchangeStatus
)
