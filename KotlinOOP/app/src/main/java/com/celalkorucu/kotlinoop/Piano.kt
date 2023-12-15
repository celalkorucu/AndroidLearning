package com.celalkorucu.kotlinoop

class Piano  : HouseDecor , Instrument{


    var brand : String? = null
    var digital : Boolean = false



    override var roomName: String
        get() = "Kitchen"
        set(value) {}
    override var roomArea: Int
        get() = 50
        set(value) {}

    constructor(brand : String , digital : Boolean ){
        this.brand = brand
        this.digital = digital

    }


}