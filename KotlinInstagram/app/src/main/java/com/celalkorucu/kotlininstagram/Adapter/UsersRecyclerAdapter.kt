package com.celalkorucu.kotlininstagram.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.celalkorucu.kotlininstagram.Model.User
import com.celalkorucu.kotlininstagram.View.FeedActivity
import com.celalkorucu.kotlininstagram.databinding.ActivityUsersBinding
import com.celalkorucu.kotlininstagram.databinding.FeedActivityRecyclerRowBinding
import com.celalkorucu.kotlininstagram.databinding.UsersActivityRecyclerRowBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.squareup.picasso.Picasso


private lateinit var database : FirebaseFirestore
class UsersRecyclerAdapter (private var usersArrayList: ArrayList<User>, private var user : User) : RecyclerView.Adapter<UsersRecyclerAdapter.UsersHolder>() {

    class UsersHolder(val usersBinding: UsersActivityRecyclerRowBinding ) : RecyclerView.ViewHolder(usersBinding.root){



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {

        val usersBinding = UsersActivityRecyclerRowBinding.inflate(LayoutInflater.from(parent.context))
        return UsersHolder(usersBinding)
    }

    override fun onBindViewHolder(holder: UsersHolder, position: Int) {

        holder.usersBinding.usersEmailText.text = usersArrayList.get(position).email
        holder.usersBinding.usersUsernameText.text = usersArrayList.get(position).username
        Picasso.get().load(usersArrayList.get(position).profilePhoto).into(holder.usersBinding.usersProfileImage)

        //FALLOW BUTTON CLICKED
        holder.usersBinding.usersFollowedButton.setOnClickListener {

            if(holder.usersBinding.usersFollowedButton.text.equals("Follow")){

                val  nullUser = User()
                notifyDataSetChanged()
                nullUser.addFriend(user.id!! , usersArrayList.get(position),user){ success ->

                    if(success){
                        notifyDataSetChanged()
                    }else{
                        Toast.makeText(holder.itemView.context , "PROBLEM" , Toast.LENGTH_LONG).show()
                    }
                }

                holder.usersBinding.usersFollowedButton.text = "Following"
                //Toast.makeText(it.context , "Hello " , Toast.LENGTH_LONG).show()
                notifyDataSetChanged()
            }else if(holder.usersBinding.usersFollowedButton.text.equals("Following")){

               //REMOVE FRIEND

            }
        }

        if(usersArrayList.get(position).email == user.email ){
            holder.usersBinding.usersFollowedButton.isEnabled = false
        }
        if(user.friendList!= null){
            println("Arkadaş sayısı : "+user.friendList!!.size)

            for(i in user.friendList!!){
                if(i.email == usersArrayList.get(position).email){
                    holder.usersBinding.usersFollowedButton.text = "Following"
                    break
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return usersArrayList.size
    }
    //ADD FRIEND
    /*
    private fun addFriend(userDocumentID : String , friendUser : User){
        database = Firebase.firestore

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


                    // RecyclerView'ı güncelle
                    //notifyDataSetChanged()

                    println("SUCCESS")
                }



                }.addOnFailureListener {
                    //FAIL
                    println("FAIL")
                }
        }
    }
    //REMOVE FRIEND
    private fun removeFriend(userDocumentID: String , friendUser: User ){

    }

     */

}