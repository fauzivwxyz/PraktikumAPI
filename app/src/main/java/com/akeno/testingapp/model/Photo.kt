package com.akeno.testingapp.model

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,

    @SerializedName("url")
    val body: String?,

    @SerializedName("thumbnailUrl")
    val thumbnail: String?
)