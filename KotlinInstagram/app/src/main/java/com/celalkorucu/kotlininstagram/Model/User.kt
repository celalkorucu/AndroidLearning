package com.celalkorucu.kotlininstagram.Model

import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.firestore
class User  : java.io.Serializable{

    var id : String? = null
    var email : String? = null
    var password : String? = null
    var username : String? = null
    var profilePhoto : String? = null
    var friendList : ArrayList<User>? = null


    constructor(){

    }

    constructor(id: String , email: String , username: String , profilePhoto: String?){
        this.id=id
        this.email=email
        this.username=username
        this.profilePhoto=profilePhoto
    }

    constructor(id: String, email: String, password: String, username: String, profilePhoto: String?, friendList: ArrayList<User>?){
        this.id=id
        this.email=email
        this.password=password
        this.username=username
        this.profilePhoto = profilePhoto
        this.friendList=friendList
    }

    //Friend
    constructor(email: String , username : String , profilePhoto: String?){
        this.email=email
        this.username=username
        this.profilePhoto = profilePhoto
    }

    fun returnUserToEmail(inputEmail : String, onUserFound: (User?) -> Unit) {

        var user = User()
        val database  = Firebase.firestore


        database.collection("Users").get().addOnSuccessListener {
            if (it.isEmpty) {
                println("COLLECTION EMPTY")
            } else {
                // COLLECTION
                for (document in it.documents) {
                    val id = document.id
                    val email = document.get("email") as String
                    val password  = document.get("password") as String
                    val profilePhoto = document.get("profilePhoto") as String
                    val username = document.get("username") as String

                    if(inputEmail == email){
                        getFriend(id){
                            if(it != null){

                                println(inputEmail +"'ın arkadaş sayısı : "+it.size)
                                //USER HAVE FRIENDS
                                user = User(id,email,password,username,profilePhoto,it)
                                onUserFound(user)
                                return@getFriend
                            }else{

                                //USER NOT HAVE A FRIEND
                                println(inputEmail + "'ın Arkadaşı yok")
                                user = User(id,email , password, username , profilePhoto , null)
                                onUserFound(user)
                                return@getFriend
                            }
                        }
                        break
                    }
                }

                return@addOnSuccessListener
            }
        }
    }

    fun getFriend(documentID : String ,onUserFound: (ArrayList<User>?) -> Unit){

        val database = Firebase.firestore
        val friendList = ArrayList<User>()

       database.collection("Users").document(documentID).collection("Friends").addSnapshotListener { value, error ->

            if(error != null){
                println("USER ERROR")
            }else{
                if(value != null){
                    if(!value.isEmpty){
                        friendList.clear()
                        for(document in value.documents){
                            val friendEmail = document.getString("email") as String
                            val friendUsername = document.getString("username") as String
                            val friendProfilePhoto = document.getString("profilePhoto") as String

                            if(friendEmail != null && friendUsername != null && friendProfilePhoto != null){

                                val friendUser = User(friendEmail!! , friendUsername!! , friendProfilePhoto)
                                friendList.add(friendUser)
                            }
                        }
                        onUserFound(friendList)
                        return@addSnapshotListener
                    }else{
                        println("USER VALUE == EMPTY")
                        onUserFound(null)
                        return@addSnapshotListener
                    }
                }else{
                    println("USER VALUE == NULL")
                }
            }
        }
    }
     //user = BİZİM KULLANICIMIZ
     //friendUser = ARKADAŞ EKLENECEK KİŞİ
     fun addFriend(userDocumentID : String , friendUser : User , user : User , onComplete : (Boolean) -> Unit){

        val database = Firebase.firestore

        if(user != null) {
            val map = HashMap<String, Any>()
            val email = friendUser.email
            val username = friendUser.username
            val profilePhoto = friendUser.profilePhoto

            map.put("email", email!!)
            map.put("username", username!!)
            map.put("profilePhoto", profilePhoto!!)

            database.collection("Users").document(userDocumentID).collection("Friends").add(map).addOnSuccessListener {
                //SUCCESS

                if(it != null){
                    if (user.friendList == null) {
                        user.friendList = ArrayList<User>()
                    }
                    user.friendList!!.add(friendUser)

                    onComplete(true)
                    println("ADD FRİEND SUCCESS")
                    return@addOnSuccessListener
                }
            }.addOnFailureListener {
                //FAIL
                onComplete(false)
                return@addOnFailureListener

            }
        }
    }


     fun returnProfilePhotoToEmail(inputEmail : String  , onUserFound: (String?) -> Unit ){

        val  database = Firebase.firestore

            database.collection("Users").whereEqualTo("email" , inputEmail).get().addOnSuccessListener {
            if(it != null){
                if(!it.isEmpty){
                    for(document in it.documents){
                        val profilePhoto = document.getString("profilePhoto")
                        onUserFound(profilePhoto)
                        return@addOnSuccessListener
                        break
                    }
                }
            }
        }.addOnFailureListener {

        }
    }

}