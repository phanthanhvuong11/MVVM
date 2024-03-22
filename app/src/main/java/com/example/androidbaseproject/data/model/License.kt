package com.example.androidbaseproject.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author vuongphan
 */
@Serializable
data class License(
    @SerialName("key") val key: String,
    @SerialName("name") val name: String,
    @SerialName("spdx_id") val spdxId: String,
    @SerialName("url") val url: String,
    @SerialName("node_id") val nodeId: String
)
