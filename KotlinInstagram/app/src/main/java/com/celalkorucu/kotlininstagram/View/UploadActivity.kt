package com.celalkorucu.kotlininstagram.View

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.celalkorucu.kotlininstagram.Model.User
import com.celalkorucu.kotlininstagram.View.FeedActivity
import com.celalkorucu.kotlininstagram.databinding.ActivityUploadBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import java.util.UUID

private lateinit var uploadBinding: ActivityUploadBinding
private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
private lateinit var permissionLauncher: ActivityResultLauncher<String>
private var selectedPicture : Uri? = null
private lateinit var auth : FirebaseAuth
private lateinit var firestore : FirebaseFirestore
private  lateinit var storage : FirebaseStorage
private lateinit var user: User

class UploadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uploadBinding = ActivityUploadBinding.inflate(layoutInflater)
        val view : View = uploadBinding.root
        setContentView(view)


        user = User()
        registerLauncher()

        auth = Firebase.auth
        firestore = Firebase.firestore
        storage = Firebase.storage

        var  intent = intent
        user = intent.getSerializableExtra("user") as User
        uploadBinding.uploadActivityUserEmailText.text = user.email
    }

    fun uploadClicked(view : View){


        val uuid = UUID.randomUUID()
        val imageName = "${uuid}.jpg"
        val reference = storage.reference
        val imageReference = reference.child("images").child(imageName)
        if(selectedPicture != null){
            imageReference .putFile(selectedPicture!!).addOnSuccessListener{
                //download URL
                val uploadPictureReference = storage.reference.child("images").child(imageName)
                uploadPictureReference.downloadUrl.addOnSuccessListener {

                    val downloadUrl = it.toString()
                    if(auth.currentUser != null ||user != null){
                        val postMap = hashMapOf<String,Any>()
                        postMap.put("email" , user.email!!)
                        postMap.put("username" , user.username!!)
                        postMap.put("downloadUrl" ,downloadUrl)
                        postMap.put("comment" , uploadBinding.commentText.text.toString())
                        postMap.put("date" , Timestamp.now())


                        firestore.collection("Posts").add(postMap).addOnSuccessListener {
                            val intent = Intent(this@UploadActivity , FeedActivity::class.java)
                            intent.putExtra("user" , user)
                            startActivity(intent)
                            finish()
                        }.addOnFailureListener{
                            Toast.makeText(this@UploadActivity , it.localizedMessage , Toast.LENGTH_LONG).show()
                        }
                    }
                }.addOnFailureListener{
                    Toast.makeText(this@UploadActivity , it.localizedMessage , Toast.LENGTH_LONG).show()

                }
            }.addOnFailureListener {
                Toast.makeText(this@UploadActivity , it.localizedMessage , Toast.LENGTH_LONG).show()
            }
        }
    }

    fun selectImageClicked(view : View){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){

            if(ContextCompat.checkSelfPermission(this , android.Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_MEDIA_IMAGES)){
                    Snackbar.make(view , "Permission needed for gallery" , Snackbar.LENGTH_INDEFINITE).setAction("Give permission"){
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
                    Snackbar.make(view , "Permission needed for gallery" , Snackbar.LENGTH_INDEFINITE).setAction("Give permission"){
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

    private fun registerLauncher(){

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                val intentFromResult = it.data
                if(intentFromResult != null){
                    selectedPicture = intentFromResult.data
                    selectedPicture.let {
                        println("SelectPicture : "+ selectedPicture.toString())
                        uploadBinding.uploadSelectImageView.setImageURI(it)
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
                Toast.makeText(this@UploadActivity , "Permission needed" , Toast.LENGTH_LONG).show()
            }
        }
    }
}