package com.celalkorucu.kotlinoop

class User {


     var name : String? = null
        get
        private  set

    var surname : String? = null
        get
        private set

     var age : Int? = null
        get
        set

    constructor(name : String , surname : String , age : Int){
        this.name = name
        this.surname = surname
        this.age = age

    }

}