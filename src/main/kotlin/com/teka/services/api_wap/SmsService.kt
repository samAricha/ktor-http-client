package com.teka.services.api_wap

import com.teka.dto.RequestDataDto
import com.teka.dto.ResultData
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.http.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

class SmsService {

    suspend fun sendMessage(
        phoneNumber:String,
        message: String
    ): ResultData {
        val url = "https://api.apiwap.com/api/v1/whatsapp/send-message"
        val toPhoneNumber = phoneNumber
//        val phoneNumber = "+254708392326"
//        val message = "Test Hello"
        val theMessage = message
        val type = "text"
        val apiKey = "124f53d639ebcb0ce13cf8e7e6305e132cd719ac492542d44bbcb3dd55c95ab7"

        val payload = RequestDataDto(
            phoneNumber = toPhoneNumber,
            message = theMessage,
            type = type
        )


        try {
            val client = HttpClient(CIO) {
                install(ContentNegotiation){
                    json()
                }
            }
            val response: HttpResponse = withContext(Dispatchers.IO) {
                client.post(url) {
                    url {
                    }
                    contentType(ContentType.Application.Json)
                    headers {
                        append(HttpHeaders.Authorization, "Bearer $apiKey")
                    }
                    setBody(payload)
                }
            }

            return ResultData(data = response.body())

        } catch (e: Exception) {
            return ResultData(error = e.message.toString())
        }
    }


}