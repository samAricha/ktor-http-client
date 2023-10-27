package com.teka.dto

import kotlinx.serialization.Serializable

@Serializable
data class RequestDataDto(
    val phoneNumber: String,
    val message: String,
    val type: String
)


@Serializable
data class ResultData(val data: String? = null, val error: String? = null)


@Serializable
data class SmsRequestDto(
    val phoneNumber: String,
    val message: String
)