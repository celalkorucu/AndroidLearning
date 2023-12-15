package com.celalkorucu.kotlininstagram.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.celalkorucu.kotlininstagram.Model.User
import com.celalkorucu.kotlininstagram.View.FeedActivity
import com.celalkorucu.kotlininstagram.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

private lateinit var mainBinding: ActivityMainBinding
private lateinit var auth : FirebaseAuth
private lateinit var database : FirebaseFirestore
private var email : String? = null
private var password : String? = null
private var number : Int? = null
private lateinit var friendList : ArrayList<User>

class MainActivity : AppCompatActivity(),java.io.Serializable {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view : View = mainBinding.root
        setContentView(view)

        auth = Firebase.auth


        /*
        val currentUser = auth.currentUser

        if(currentUser != null){
            val intent = Intent(this@MainActivity , FeedActivity::class.java)
            val  user =User()
            user.returnUserToEmail(auth.currentUser!!.email!!){

                intent.putExtra("user" , it)
                startActivity(intent)
                finish()
            }
        }

         */

    }

    fun signInClicked(view : View){

        println("Clicked")
        email  = mainBinding.userEmailText.text.toString()
        password = mainBinding.userPasswordText.text.toString()

        if(email.equals("") || password.equals("")){
            Toast.makeText(this , "Enter email and password" , Toast.LENGTH_LONG)
        }else{
            auth.signInWithEmailAndPassword(email!!, password!!).addOnSuccessListener {
                //Success


                var user = User()
                user.returnUserToEmail(it.user!!.email!!){

                    if (user != null) {
                        // USER FOUND

                        val intent = Intent(this@MainActivity , FeedActivity::class.java)
                        intent.putExtra("user" ,it!!)
                        startActivity(intent)
                        finish()

                    } else {

                        // USER NOT FOUND
                        Toast.makeText(this@MainActivity , "USER NOT FOUND TRY AGAIN" , Toast.LENGTH_LONG).show()
                    }
                }
            }.addOnFailureListener {
                //Fail
                Toast.makeText(this@MainActivity , it.localizedMessage , Toast.LENGTH_LONG).show()
            }
        }
    }

    fun logUpClicked(view : View){

        val intent = Intent(this@MainActivity , LogUpActivity::class.java)
        startActivity(intent)
        finish()
    }

}
