package com.example.androidbaseproject.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author vuongphan
 */
@Serializable
open class BaseResponse(
    @SerialName("message") val message: String? = ""
)
