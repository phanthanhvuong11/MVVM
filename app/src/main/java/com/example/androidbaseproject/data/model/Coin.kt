package com.example.androidbaseproject.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Coin(@SerialName("item") val item: Item)