package com.celalkorucu.kotlininstagram.Model

import com.celalkorucu.kotlininstagram.Adapter.FeedRecyclerAdapter
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

private lateinit var database : FirebaseFirestore
private  lateinit var feedAdapter : FeedRecyclerAdapter

class Post  {


      var id : String? = null
      var email : String? = null
      var comment : String? = null
      var downloadUrl : String? = null
      var username : String? = null
      var likesArray : ArrayList<User>? = null



      constructor(id: String, email: String, comment:String, downloadUrl: String, username: String, likesArray: ArrayList<User>?){
         this.id = id
         this.email = email
         this.comment=comment
         this.downloadUrl = downloadUrl
         this.username = username
         this.likesArray = likesArray
      }

      constructor()


      //ADD LIKES -> CONTROL == ADD , REMOVE LIKE -> CONTROL == 'DOCUMENT ID'
      fun addLikeButtonClicked(documentID : String ,userLiked : User , postArrayList : ArrayList<Post>){

           database = Firebase.firestore

           if(userLiked != null){
               var addLike = HashMap<String,Any>()
               addLike.put("email",userLiked.email!!)
               addLike.put("username",userLiked.username!!)
               addLike.put("profilePhoto",userLiked.profilePhoto!!)

                   //ADD LIKE
               database.collection("Posts").document(documentID).collection("Likes").add(addLike).addOnSuccessListener {

                   println("SUCCESS ADD LIKE")
                   feedAdapter = FeedRecyclerAdapter(postArrayList, userLiked)
                   feedAdapter.notifyDataSetChanged()

                   }.addOnFailureListener {
                   println("FAIL ADD LIKE")
                   }
           }
      }

      fun removeLikeButtonClicked(postDocumentID : String ,user : User){

          database = Firebase.firestore

          database.collection("Posts").document(postDocumentID).collection("Likes").addSnapshotListener { value, error ->

              if(error != null){
                  println("FAIL REMOVE ERROR")
              }else{
                  if (value !=null){
                      if(!value.isEmpty){

                          for(document in value.documents){
                              val likeEmail = document.getString("email") as String
                              if(likeEmail.equals(user.email)){

                                  database.collection("Posts").document(postDocumentID).collection("Likes").document(document.id).delete().addOnSuccessListener {
                                      println("SUCCESS REMOVE POST")
                                  }.addOnFailureListener {
                                      println("FAIL REMOVE POST")
                                  }
                                  break
                              }
                          }
                      }
                  }
              }
          }
      }

}



