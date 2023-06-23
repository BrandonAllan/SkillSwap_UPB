package com.example.skillswap_upb.adapter
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillswap_upb.ChatActivity
import com.example.skillswap_upb.ItemUser
import com.example.skillswap_upb.R

class UserAdapter(var context:Context, var userlist: ArrayList<ItemUser>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_user_chat, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userlist[position]
        holder.textName.text = currentUser.name
        /*Glide.with(context).load(user.profileImage)
            .placeholder(R.drawable.profile_picture)
            .into(holder.binding.userProfileImage)*/
        holder.itemView.setOnClickListener{
            val intent = Intent(context,ChatActivity::class.java)

            intent.putExtra("name",currentUser.name)
            intent.putExtra("uid",currentUser.uid)
            context.startActivity(intent)
        }
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName = itemView.findViewById<TextView>(R.id.displayNameText)
    }
}