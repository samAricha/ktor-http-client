package com.teka.routes

import com.teka.dto.SmsRequestDto
import com.teka.services.api_wap.SmsService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking

fun Route.smsRouting(){

    route("/api/sms") {
         post{
             val smsRequest = call.receive<SmsRequestDto>()

             val smsService = SmsService()
             val result = runBlocking {
                 smsService.sendMessage(
                     phoneNumber = smsRequest.phoneNumber,
                     message = smsRequest.message
                 )
             }

             if (result.error != null) {
                 println(">>>> inside error")
                 call.respond(HttpStatusCode.InternalServerError, result.error)
             } else {
                 println(">>>> outside error")
                 call.respond(HttpStatusCode.OK, result)
             }
             println(">>>> inside nothing $result")

             call.respond(result)
        }
    }


}