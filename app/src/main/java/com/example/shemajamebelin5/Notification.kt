package com.example.shemajamebelin5

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wrapper(@Json(name = "list") val list: List<Notifications>)
data class Notifications(
    val id: Int,
    val image: String?,
    val owner: String,
    @Json(name = "last_message")
    val lastMessage: String,
    @Json(name = "last_active")
    val lastActive: String,
    @Json(name = "unread_messages")
    val unreadMessages: Int,
    @Json(name = "is_typing")
    val isTyping: Boolean,
    @Json(name = "last_message_text")
    val lastMessageText: String
)

enum class NotificationTypes(val text: String) {
    TEXT("text"),
    FILE("file"),
    VOICE("voice")
}
