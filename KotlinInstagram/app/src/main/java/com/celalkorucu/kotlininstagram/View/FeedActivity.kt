package com.celalkorucu.kotlininstagram.View

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.celalkorucu.kotlininstagram.*
import com.celalkorucu.kotlininstagram.Adapter.FeedRecyclerAdapter
import com.celalkorucu.kotlininstagram.Model.Post
import com.celalkorucu.kotlininstagram.Model.User
import com.celalkorucu.kotlininstagram.databinding.ActivityFeedBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

private  lateinit var feedBinding: ActivityFeedBinding
private  lateinit var auth : FirebaseAuth
private  lateinit var db : FirebaseFirestore
private  lateinit var storage : FirebaseStorage
private  lateinit var postArrayList : ArrayList<Post>
private  lateinit var feedAdapter : FeedRecyclerAdapter
private  lateinit var feedPostList  : kotlin.collections.ArrayList<Post>
private  lateinit var user : User
private  lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
private  lateinit var permissionLauncher: ActivityResultLauncher<String>
private var selectedPicture : Uri? = null
private  lateinit var likeList : kotlin.collections.ArrayList<User>
class FeedActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedBinding = ActivityFeedBinding.inflate(layoutInflater)
        val view : View = feedBinding.root
        setContentView(view)

        val intent = intent

        auth  = Firebase.auth
        db = Firebase.firestore
        storage = Firebase.storage

        postArrayList = ArrayList()

        if(intent != null){
            user = intent.getSerializableExtra("user") as User
            if(user != null) {
                println("Intent : " + user.email)
                println("user : "+user.email)
            }
        }
        if(user.friendList == null){
            user.friendList = ArrayList()
        }else{
            for(i in user.friendList!!){
                println("-> "+ i.username)
            }
        }

        registerLauncher()

        getData()



        if(postArrayList != null){

            feedBinding.recyclerView.layoutManager = LinearLayoutManager(this@FeedActivity)
            feedAdapter = FeedRecyclerAdapter(postArrayList!!,user)
            feedBinding.recyclerView.adapter = feedAdapter

        }
    }

    private  fun getData(){

        db.collection("Posts").orderBy("date" , Query.Direction.DESCENDING).addSnapshotListener { value, error ->

            if(error != null){
                Toast.makeText(this@FeedActivity , error.localizedMessage , Toast.LENGTH_LONG).show()
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

                            val post = Post(id,userEmail , comment , downloadUrl , username ,null)
                            println("post ID : "+post.id)
                            postArrayList.add(post)

                        }
                        feedAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //GO TO UPLOAD ACTIVITY
        if(item.itemId == R.id.add_post){
            //Upload Activity
            val intent = Intent(this@FeedActivity , UploadActivity::class.java)
            if(auth.currentUser != null){
                intent.putExtra("user" , user)
                startActivity(intent)

            }
        }
        //SIGN OUT
        else if (item.itemId == R.id.sign_out){
            auth.signOut()
            val intent = Intent(this@FeedActivity , MainActivity::class.java)
            startActivity(intent)
            finish()

        }
        //GO TO PROFİLE ACTIVITY
        else if(item.itemId == R.id.profile_activity){
            val intent = Intent(this@FeedActivity , ProfileActivity::class.java)
            intent.putExtra("user" , user )
            startActivity(intent)

        }
        //GO TO USERS ACTIVITY
        else if (item.itemId == R.id.users_activity){
            val intent = Intent(this@FeedActivity , UsersActivity::class.java)
            intent.putExtra("user" , user)
            intent.putExtra("usersOrFriends" , "users")
            postArrayList.clear()
            startActivity(intent)
        }
        //CHANGE PROFILE PHOTO
        else if (item.itemId == R.id.change_profile_photo){
            changeProfilePhoto()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registerLauncher(){

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                val intentFromResult = it.data
                if(intentFromResult != null){
                    selectedPicture = intentFromResult.data
                    selectedPicture.let {


                        //FIREBASE AND USER OBJECT SET PROFILE PHOTO

                        val uuid = UUID.randomUUID()
                        val imageName = "${uuid}.jpg"
                        val reference = storage.reference
                        val imageReference = reference.child("profileImages").child(imageName)

                        imageReference .putFile(selectedPicture!!).addOnSuccessListener {
                            //DOWNLOAD URL
                            val uploadPictureReference = storage.reference.child("profileImages").child(imageName)
                            uploadPictureReference.downloadUrl.addOnSuccessListener {
                                val downloadUrl = it.toString()
                                //FIREBASE USER SET PROFILE PHOTO
                                //USER OBJECT SET PROFILE PHOTO

                                val update = HashMap<String , Any>()
                                update.put("profilePhoto" , downloadUrl)

                                db.collection("Users").document(user.id!!).update(update).addOnSuccessListener {

                                    user.profilePhoto = downloadUrl
                                    Toast.makeText(this@FeedActivity , "Success" , Toast.LENGTH_SHORT).show()

                                }.addOnFailureListener {
                                    Toast.makeText(this@FeedActivity , it.localizedMessage , Toast.LENGTH_LONG).show()
                                }



                            }.addOnFailureListener {
                                Toast.makeText(this@FeedActivity , it.localizedMessage , Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }
        }
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            if(it){
                //Permission Granted
                val intentToGallery = Intent(Intent.ACTION_PICK , MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }else{
                //Permission Denied
                Toast.makeText(this@FeedActivity , "Permission needed" , Toast.LENGTH_LONG).show()
            }
        }
    }

    private  fun changeProfilePhoto(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(this , android.Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_MEDIA_IMAGES)){
                    Snackbar.make(feedBinding.root , "Permission needed for gallery" , Snackbar.LENGTH_INDEFINITE).setAction("Give permission"){
                        //Request Permission
                        permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)

                    }.show()
                }else{
                    //Request Permission
                    permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)

                }
            }else{
                val intentToGallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                //Start Activity for result
                activityResultLauncher.launch(intentToGallery)
            }
        }else{

            if(ContextCompat.checkSelfPermission(this , android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                    Snackbar.make(feedBinding.root , "Permission needed for gallery" , Snackbar.LENGTH_INDEFINITE).setAction("Give permission"){
                        //Request Permission
                        permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)

                    }.show()
                }else{
                    //Request Permission
                    permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)

                }
            }else{
                val intentToGallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                //Start Activity for result
                activityResultLauncher.launch(intentToGallery)
            }
        }
    }

    private fun returnLikedList(postID : String  ,onUserFound: (ArrayList<User>?) -> Unit ) {

        var list = ArrayList<User>()

            db.collection("Posts").document(postID).collection("Likes").addSnapshotListener { value, error ->


            if(error == null){
                if(value != null) {
                    if (!value.isEmpty) {
                        for (document in value.documents) {

                            val likeEmail = document.getString("email") as String
                            val likeUsername = document.getString("username") as String
                            val likeProfilePhoto = document.getString("profilePhoto") as String
                            val likeUser = User(likeEmail!!, likeUsername!!, likeProfilePhoto!!)

                            list.add(likeUser)
                            onUserFound(list)
                            return@addSnapshotListener
                        }
                    }else{
                        onUserFound(null)
                        return@addSnapshotListener
                    }
                }
            }
        }
    }

}