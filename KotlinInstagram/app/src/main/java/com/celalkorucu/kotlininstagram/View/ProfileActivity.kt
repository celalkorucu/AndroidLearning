package com.celalkorucu.kotlininstagram.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.celalkorucu.kotlininstagram.Adapter.FeedRecyclerAdapter
import com.celalkorucu.kotlininstagram.Model.Post
import com.celalkorucu.kotlininstagram.Model.User
import com.celalkorucu.kotlininstagram.R
import com.celalkorucu.kotlininstagram.databinding.ActivityProfileBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.squareup.picasso.Picasso

private lateinit var profileBinding: ActivityProfileBinding
private lateinit var user : User
private lateinit var database : FirebaseFirestore
private  lateinit var feedPostArrayList: ArrayList<Post>
private  lateinit var profileAdapter : FeedRecyclerAdapter
private lateinit var postArrayList: ArrayList<Post>
class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileBinding = ActivityProfileBinding.inflate(layoutInflater)
        val view : View  = profileBinding.root
        setContentView(view)


        val intent = intent
        user =  intent.getSerializableExtra("user") as User
        postArrayList = ArrayList()

        database = Firebase.firestore
        getData()


        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenHeight = displayMetrics.heightPixels

        profileBinding.profilePostRecyclerView.minimumHeight = screenHeight
        Picasso.get().load(user.profilePhoto).into(profileBinding.profilePhotoImage)
        profileBinding.profileEmailText.text = user.email
        profileBinding.profileUsernameText.text = user.username
        if(user.friendList != null){
            profileBinding.followCount.text = "${user.friendList!!.size}"
        }else{
            profileBinding.followCount.text = "0"
        }

        profileBinding.profilePostRecyclerView.layoutManager = LinearLayoutManager(this@ProfileActivity)
        profileAdapter = FeedRecyclerAdapter(postArrayList,user)
        profileBinding.profilePostRecyclerView.adapter = profileAdapter

    }

    private  fun getData(){
        database.collection("Posts").whereEqualTo("email" , user.email).addSnapshotListener { value, error ->

            if(error != null){
                Toast.makeText(this@ProfileActivity , error.localizedMessage , Toast.LENGTH_LONG).show()
                println("errorprofileac")
                println(error.localizedMessage)
            }else{
                if(value != null){
                    if(!value.isEmpty){
                        val documents = value.documents
                        postArrayList.clear()

                        for(document in documents){

                            //costing
                            val id = document.id
                            val comment = document.get("comment") as String
                            val userEmail = document.get("email") as String
                            val downloadUrl = document.get("downloadUrl") as String
                            val username = document.get("username") as String

                            var  post = Post(id, userEmail ,comment,downloadUrl,username ,null)
                            postArrayList.add(post)
                        }
                        profileAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    fun profileFriendsClicked(view : View){
        var friendsUser = User()
        if(user != null){

            friendsUser.getFriend(user.id!!){

                if(it != null){
                    val intent = Intent(this@ProfileActivity , UsersActivity::class.java)
                    intent.putExtra("usersOrFriends","friends")
                    intent.putExtra("friendsArray" , it)
                    intent.putExtra("user", user)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@ProfileActivity , "You not have friends" ,Toast.LENGTH_LONG).show()
                }

            }
        }
    }
}