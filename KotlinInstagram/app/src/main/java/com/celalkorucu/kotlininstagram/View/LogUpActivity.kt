package com.celalkorucu.kotlininstagram.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.celalkorucu.kotlininstagram.Model.User
import com.celalkorucu.kotlininstagram.databinding.ActivityLogUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage

private lateinit var logUpBinding: ActivityLogUpBinding
private lateinit var auth: FirebaseAuth
private lateinit var database : FirebaseFirestore
private lateinit var storage : FirebaseStorage
private var username : String? = null
private var email : String? = null
private var password  : String? = null


class LogUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logUpBinding = ActivityLogUpBinding.inflate(layoutInflater)
        val view : View = logUpBinding.root
        setContentView(view)


        auth = Firebase.auth
        database = Firebase.firestore
        storage = Firebase.storage


    }
    fun logUpButtonClicked(view : View){

        username = logUpBinding.logUpUserNameText.text.toString()
        email  = logUpBinding.logUpEmailText.text.toString()
        password = logUpBinding.logUpPasswordText.text.toString()

        if(email.equals("") || password.equals("") || username.equals("")){
            Toast.makeText(this , "Enter email and password" , Toast.LENGTH_LONG).show()
        }
        else{

            auth.createUserWithEmailAndPassword(email!! , password!!).addOnSuccessListener {
                val user = User(auth.currentUser!!.uid , email!! , password!!  , username!! , "https://firebasestorage.googleapis.com/v0/b/kotlininstagram-f96f9.appspot.com/o/images%2Fprofilephoto.jpg?alt=media&token=5fe82ddb-74bc-447d-a556-3ad55e9d259f&_gl=1*136nobx*_ga*MTc0NDg4NDgzLjE2ODAyMTMzNTc.*_ga_CW55HF8NVT*MTY5NzkzNjg0MC4zMS4xLjE2OTc5Mzk1NzAuNTUuMC4w"  , null)
                if(user != null){
                    addDatabaseUser(user)
                }

            }.addOnFailureListener {
                Toast.makeText(this@LogUpActivity , it.localizedMessage , Toast.LENGTH_LONG).show()
                println("LOG UP FAIL")
            }
        }
    }

    private fun addDatabaseUser(user : User){

       if(user.profilePhoto != null ||user != null){

           val userMap = HashMap<String , Any>()
           userMap.put("email",user.email!!)
           userMap.put("password" , user.password!!)
           userMap.put("username",user.username!!)
           userMap.put("profilePhoto" ,user.profilePhoto!!)

           database.collection("Users").add(userMap).addOnSuccessListener {
           //Success
           Toast.makeText(this@LogUpActivity , "Success" , Toast.LENGTH_LONG).show()

               val intent = Intent(this@LogUpActivity , MainActivity::class.java)
               startActivity(intent)
               finish()

           }.addOnFailureListener {
               Toast.makeText(this@LogUpActivity , it.localizedMessage,Toast.LENGTH_LONG)
           }
       }
    }

    fun logUpProfilePhotoClicked(view : View){

    }
}