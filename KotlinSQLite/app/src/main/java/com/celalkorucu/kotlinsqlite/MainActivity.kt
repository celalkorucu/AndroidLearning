package com.celalkorucu.kotlinsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {

            var myDatabase = this.openOrCreateDatabase("Musicians" , MODE_PRIVATE , null)
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musician (id INTEGER PRIMARY KEY , name VARCHAR , surname VARCHAR)")


            /*
            myDatabase.execSQL("INSERT INTO musician (name , surname) VALUES ('İsmail' , 'KORUCU')")
            myDatabase.execSQL("INSERT INTO musician (name , surname) VALUES ('Samet' , 'KORUCU')")
            myDatabase.execSQL("INSERT INTO musician (name , surname) VALUES ('Sinan' , 'KORUCU')")
            myDatabase.execSQL("INSERT INTO musician (name , surname) VALUES ('Memiş' , 'KORUCU')")
            myDatabase.execSQL("INSERT INTO musician (name , surname) VALUES ('Melisa' , 'Çakıcı')")
            myDatabase.execSQL("INSERT INTO musician (name , surname) VALUES ('Meryem' , 'Demirörs')")
            myDatabase.execSQL("INSERT INTO musician (name , surname) VALUES ('Ravza' , 'Tencireci')")
            myDatabase.execSQL("INSERT INTO musician (name , surname) VALUES ('İmran' , 'Ayaydın')")



             */

            // myDatabase.execSQL("DELETE  FROM musician")
             myDatabase.execSQL("UPDATE musician SET  surname = 'Çakıcı' WHERE name = 'Melisa'")

            val cursor = myDatabase.rawQuery("SELECT * FROM musician " ,null)

            var idIx = cursor.getColumnIndex("id")
            var nameIx = cursor.getColumnIndex("name")
            var surnameIx = cursor.getColumnIndex("surname")



            while (cursor.moveToNext()){

                println("ID : " + cursor.getInt(idIx))
                println("Name : "+ cursor.getString(nameIx))
                println("Surname : "+cursor.getString(surnameIx))

            }

            cursor.close()


        }catch (e : Exception){
            e.printStackTrace()
        }

    }
}