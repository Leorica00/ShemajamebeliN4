package com.example.shemajamebelin5

import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shemajamebelin5.databinding.NotificationRecyclerItemBinding

class NotificationsRecyclerAdapter :
    ListAdapter<Notifications, NotificationsRecyclerAdapter.NotificationsViewHolder>(
        MyItemDiffCallback()
    ) {

    fun setData(data: List<Notifications>?) {
        if (data != null) {
            submitList(data.toMutableList())
        } else {
            // Handle the case where data is null
            // You might want to log an error or provide some default behavior
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsViewHolder {
        return NotificationsViewHolder(
            NotificationRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
        )
    }

    override fun onBindViewHolder(holder: NotificationsViewHolder, position: Int) {
        holder.bind()
    }

    inner class NotificationsViewHolder(
        private val binding: NotificationRecyclerItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            d("checkIFWorks", currentList[0].toString())
            val currentNotification: Notifications = currentList[adapterPosition]
            with(binding) {
                Glide.with(itemView.context).load(currentNotification.image).into(binding.ivProfile)
                tvOwner.text = currentNotification.owner
                tvTime.text = currentNotification.lastActive
                if (currentNotification.isTyping == true) {
                    tvNumberOfNotifications.visibility = View.VISIBLE
                    tvNumberOfNotifications.text = ". ."
                } else if (currentNotification.unreadMessages > 0) {
                    tvNumberOfNotifications.visibility = View.VISIBLE
                    tvNumberOfNotifications.text = currentNotification.unreadMessages.toString()
                    tvNumberOfNotifications.setBackgroundResource(R.drawable.costume_image_background)
                }
                when (currentNotification.lastMessageText) {
                    NotificationTypes.TEXT.text -> tvLastMessage.text =
                        currentNotification.lastMessage

                    NotificationTypes.VOICE.text -> {
                        tvLastMessage.text = "Sent a voice message"
                        ivVoice.visibility = View.VISIBLE
                    }

                    NotificationTypes.FILE.text -> {
                        tvLastMessage.text = "Sent an attachment"
                        ivVoice.visibility = View.VISIBLE
                        ivVoice.setImageResource(R.drawable.ic_attachment)
                    }
                }
            }
        }
    }

    private class MyItemDiffCallback : DiffUtil.ItemCallback<Notifications>() {

        override fun areItemsTheSame(
            oldItem: Notifications,
            newItem: Notifications
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Notifications,
            newItem: Notifications
        ): Boolean {
            return oldItem == newItem
        }
    }
}