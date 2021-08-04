package dev.kibet.movetechtest.data.repository

import dev.kibet.movetechtest.data.api.MoveSmsApi
import dev.kibet.movetechtest.data.request.Response
import dev.kibet.movetechtest.data.request.SendSms
import dev.kibet.movetechtest.others.Resource
import javax.inject.Inject

class Repository @Inject constructor(private val moveSmsApi: MoveSmsApi) {

    suspend fun sendSms(
        username: String,
        api_key: String,
        sender: String,
        to: String,
        message: String,
        msgtype: String,
        dlr: String
    ): Resource<Response> {

        return try {
            val response = moveSmsApi.sendSMS(
                SendSms(
                    username = username,
                    api_key = api_key,
                    sender = sender,
                    to = to,
                    message = message,
                    msgtype = msgtype,
                    dlr = dlr
                )
            )
            Resource.success(response)
        } catch (e: Exception) {
            Resource(e.message.toString(), null, "Nothing")
        }

        //{
        // return if (e is HttpException) {
        //Resource.error(e.message(), null)
        //} else {
        //Resource.error(
        //"Couldn't connect to the servers. Check your internet connection,
        //  null
        //)
    }

}