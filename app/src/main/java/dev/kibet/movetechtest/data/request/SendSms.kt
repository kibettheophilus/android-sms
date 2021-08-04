package dev.kibet.movetechtest.data.request

data class SendSms(
    val username: String,
    val api_key: String,
    val sender: String,
    val to: String,
    val message: String,
    val msgtype: String,
    val dlr: String
)
