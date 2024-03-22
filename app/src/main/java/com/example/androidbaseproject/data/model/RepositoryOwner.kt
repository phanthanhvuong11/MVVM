package com.example.androidbaseproject.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author vuongphan
 */
@Serializable
data class RepositoryOwner(
    @SerialName("id") val id: Int,
    @SerialName("login") val login: String,
    @SerialName("node_id") val nodeId: String,
    @SerialName("avatar_url") val avatarUrl: String,
    @SerialName("gravatar_id") val gravatarId: String,
    @SerialName("url") val url: String,
    @SerialName("followers_url") val followersUrl: String,
    @SerialName("html_url") val htmlUrl: String,
    @SerialName("following_url") val followingUrl: String,
    @SerialName("gists_url") val gistsUrl: String,
    @SerialName("starred_url") val starredUrl: String,
    @SerialName("subscriptions_url") val subscriptionsUrl: String,
    @SerialName("organizations_url") val organizationsUrl: String,
    @SerialName("repos_url") val reposUrl: String,
    @SerialName("events_url") val eventsUrl: String,
    @SerialName("received_events_url") val receivedEventsUrl: String,
    @SerialName("type") val type: String,
    @SerialName("site_admin") val siteAdmin: Boolean
)
