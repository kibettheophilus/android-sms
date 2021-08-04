package dev.kibet.movetechtest.data.api

import dev.kibet.movetechtest.data.request.Response
import dev.kibet.movetechtest.data.request.SendSms
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface MoveSmsApi {

    @POST("api/compose")
    suspend fun sendSMS(
        @Body sendSms: SendSms
    ): Response

}