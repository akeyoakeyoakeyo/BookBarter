package com.akeyo.bookbarter.models

class Book {
    var title:String=""
    var author:String=""
    var description:String=""
    var imageUrl:String=""
    var ownerid:String=""
    var condition:String=""
    var id:String=""



    constructor(title:String,author:String,description:String,imageUrl:String, ownerid:String, condition:String, id:String){
        this.title=title
        this.author=author
        this.description=description
        this.imageUrl=imageUrl
        this.ownerid=ownerid
        this.condition=condition
        this.id=id

    }
    constructor()
}