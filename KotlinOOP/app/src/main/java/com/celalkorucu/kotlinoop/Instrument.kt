package com.celalkorucu.kotlinoop

interface Instrument {

    fun brandInfo(brandName : String) : String {
        return "Instrument Name : ${brandName}"
    }

    fun digitalInfo(digital : Boolean) : Boolean{

        return digital
    }
}