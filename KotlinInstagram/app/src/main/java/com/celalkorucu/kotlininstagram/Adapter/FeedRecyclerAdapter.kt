package com.celalkorucu.kotlininstagram.Adapter

import android.app.Application
import android.app.Dialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.celalkorucu.kotlininstagram.Model.Post
import com.celalkorucu.kotlininstagram.Model.User
import com.celalkorucu.kotlininstagram.R
import com.celalkorucu.kotlininstagram.R.drawable
import com.celalkorucu.kotlininstagram.databinding.FeedActivityRecyclerRowBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.squareup.picasso.Picasso

private lateinit var database : FirebaseFirestore
private lateinit var list : ArrayList<User>



class FeedRecyclerAdapter (private var postList : ArrayList<Post> , private var user : User): RecyclerView.Adapter<FeedRecyclerAdapter.PostHolder>() {


    class PostHolder(val binding: FeedActivityRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {

        val binding = FeedActivityRecyclerRowBinding.inflate(LayoutInflater.from(parent.context))
        return PostHolder(binding)

    }

    override  fun onBindViewHolder(holder: PostHolder, position: Int) {
        var postUser = User()
        var post = Post()

        println("onbindview postsize : "+postList.size)

        if(postList != null){
            println("POSTLİSTNULL")
        }
        if(postList.get(position) != null){




            holder.binding.userNameText.text = postList.get(position).username
            holder.binding.commentText.text = postList.get(position).comment
            Picasso.get().load(postList.get(position).downloadUrl).into(holder.binding.postImage);

            returnProfilePhotoToEmail(postList.get(position).email!!){
                if(it!= null){
                    Picasso.get().load(it).into(holder.binding.postProfilePhoto)
                }
            }
        }

        returnLikeList(postList.get(position).id!!){

            holder.binding.postLiked.setImageResource(drawable.notlike)
            if (it != null) {

                for (i in it){
                    if(user.email == i.email){
                        holder.binding.postLiked.setImageResource(drawable.like)
                    }
                }

                postList.get(position).likesArray = it
                print(postList.get(position).comment + "   ->   ")
                println(postList.get(position).likesArray!!.size)

                holder.binding.postLikeCount.text = "${it.size}  Liked"
            }
        }

        //POST LIKE BUTTON CLICKED
        holder.binding.postLiked.setOnClickListener {

            val postID = postList.get(position).id
            if(it != null){

                var isAlreadyLiked = false
                val likedPost = postList.get(position).likesArray

                if(likedPost != null){

                    for (likedUser in likedPost) {
                        if (likedUser.id == user.id) {
                            isAlreadyLiked = true
                            break
                        }
                    }

                }

                if(!isAlreadyLiked){
                    post.addLikeButtonClicked(postID!! , user , postList)
                    notifyDataSetChanged()
                }

            }

/*
            returnLikeList(postList.get(position).id!!){
                if(it!=null){

                    var isAlreadyLiked = false
                    for (likedUser in it) {
                        if (likedUser.id == user.id) {
                            isAlreadyLiked = true
                            break
                        }
                    }


                    val postID = postList.get(position).id
                    val likedPost = postList.find { it.id == postID }

                    if (!isAlreadyLiked) {
                        if(likedPost != null){
                            post.addLikeButtonClicked(postID!!, user , postList)
                            likedPost.likesArray?.add(user)

                            // Post listesini güncelle
                            val postIndex = postList.indexOf(likedPost)
                            postList[postIndex] = likedPost

                            holder.binding.postLiked.setImageResource(R.drawable.like)
                        }
                    }
                }
            }

 */
        }

        //POST LIKE COUNT
        holder.binding.postLikeCount.setOnClickListener {
            println("Click")
        }
    }

    override fun getItemCount(): Int {
       return  postList.size
    }

    private fun returnLikeList(postID : String , onUserFound: (ArrayList<User>?) -> Unit){
        database = Firebase.firestore
        list = ArrayList()
        database.collection("Posts").document(postID).collection("Likes").addSnapshotListener { value, error ->

            if(error != null){
                println("Error")
            }else{
                if(value!= null){
                    if(!value.isEmpty){
                        val documents = value.documents
                        list.clear()
                        for(document in documents){
                            val id = document.id
                            val e = document.getString("email")
                            val u = document.getString("username")
                            val pp = document.getString("profilePhoto")

                           if(e != null && u != null){
                               val lUser = User(e,u,pp)
                               list.add(lUser)
                           }
                        }

                        onUserFound(list)
                        return@addSnapshotListener

                    }
                }
            }
        }
    }


    private fun returnProfilePhotoToEmail(inputEmail : String  , onUserFound: (String?) -> Unit ){
        database = Firebase.firestore

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

