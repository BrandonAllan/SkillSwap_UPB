package com.example.skillswap_upb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skillswap_upb.databinding.ReceiveMessageBinding
import com.example.skillswap_upb.databinding.SendMessageBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(var context:Context, messages:ArrayList<Message>?, senderRoom:String, receiverRoom:String):RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    lateinit var messages:ArrayList<Message>
    val Item_sent=1
    val Item_receive=2
    val senderRoom:String
    val receiverRoom:String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == Item_sent){
            val view = LayoutInflater.from(context).inflate(R.layout.send_message, parent, false)
            SentMsgHolder(view)
        } else{
            val view = LayoutInflater.from(context).inflate(R.layout.send_message, parent, false)
            ReceiveMsgHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val messages = messages[position]
            return if (FirebaseAuth.getInstance().uid == messages.senderId){
                Item_sent
            }else{
                Item_receive
            }
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        if(holder.javaClass == SentMsgHolder::class.java){
            val viewHolder = holder as SentMsgHolder
            if (message.message.equals("photo")){
                viewHolder.binding.imageChat.visibility = View.VISIBLE
                viewHolder.binding.message.visibility = View.GONE
                viewHolder.binding.messageLinear.visibility = View.GONE
                Glide.with(context)
                    .load(message.imageUrl)
                    .placeholder(R.drawable.chat_image)
                    .into(viewHolder.binding.imageChat)
                }
            }else {
                val viewHolder = holder as ReceiveMsgHolder
                if(message.message.equals("photo")){
                    viewHolder.binding.imageChat.visibility = View.VISIBLE
                    viewHolder.binding.message.visibility = View.GONE
                    viewHolder.binding.messageLinear.visibility = View.GONE
                    Glide.with(context)
                        .load(message.imageUrl)
                        .placeholder(R.drawable.chat_image)
                        .into(viewHolder.binding.imageChat)
                }
        }
    }

    inner class SentMsgHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var binding:SendMessageBinding = SendMessageBinding.bind(itemView)
    }

    inner class ReceiveMsgHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var binding:ReceiveMessageBinding = ReceiveMessageBinding.bind(itemView)
    }
    init{
        if(messages!=null){
            this.messages = messages
        }
        this.senderRoom = senderRoom
        this.receiverRoom = receiverRoom
    }

}