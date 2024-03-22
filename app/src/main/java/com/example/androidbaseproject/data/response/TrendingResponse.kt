package com.example.androidbaseproject.data.response

import com.example.androidbaseproject.data.model.Coin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrendingResponse(
    @SerialName("coins") val coins: List<Coin>
) : BaseResponse()