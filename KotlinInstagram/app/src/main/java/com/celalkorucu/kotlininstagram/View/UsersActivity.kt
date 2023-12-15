package com.celalkorucu.kotlininstagram.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.celalkorucu.kotlininstagram.Adapter.FeedRecyclerAdapter
import com.celalkorucu.kotlininstagram.Adapter.UsersRecyclerAdapter
import com.celalkorucu.kotlininstagram.Model.User
import com.celalkorucu.kotlininstagram.databinding.ActivityUsersBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

private  lateinit var usersBinding : ActivityUsersBinding
private  lateinit var database: FirebaseFirestore
private lateinit var usersAdapter:UsersRecyclerAdapter
private  lateinit var user : User
private  lateinit var usersArrayList: ArrayList<User>
private lateinit var friendsArrayList : ArrayList<User>

class UsersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usersBinding = ActivityUsersBinding.inflate(layoutInflater)
        val view = usersBinding.root
        setContentView(view)
        database = Firebase.firestore


        user = intent.getSerializableExtra("user") as User
        val usersOrFriends = intent.getStringExtra("usersOrFriends") as String

        usersArrayList = ArrayList()
        friendsArrayList = ArrayList()


        if(usersOrFriends.equals("users")){

            //ACTİVİTY USERSLARI GOSTERIR
            getAllUsers()
            usersAdapter = UsersRecyclerAdapter(usersArrayList,user)

        }else if (usersOrFriends.equals("friends")){

            friendsArrayList = intent.getSerializableExtra("friendsArray") as ArrayList<User>
            for(i in friendsArrayList){
                println(i.profilePhoto)
            }
            //ACTIVITY FRIENDLERI GOSTERIR
            usersAdapter = UsersRecyclerAdapter(friendsArrayList , user)

        }
        usersBinding.usersRecyclerView.adapter = usersAdapter
        usersBinding.usersRecyclerView.layoutManager = LinearLayoutManager(this@UsersActivity)

    }

    private  fun getAllUsers(){
        database.collection("Users").get().addOnSuccessListener {
            if (it.isEmpty) {
                println("Koleksiyon boş.")
            } else {
                // COLLECTION

                for (document in it.documents) {
                    val id = document.id
                    val email = document.get("email") as String
                    val profilePhoto = document.get("profilePhoto") as String
                    val username = document.get("username") as String

                    var user = User(id,email,username,profilePhoto)
                    usersArrayList.add(user)
                }
            }
            usersAdapter.notifyDataSetChanged()
        }.addOnFailureListener {
            Toast.makeText(this@UsersActivity , it.localizedMessage , Toast.LENGTH_LONG).show()
        }
    }

}