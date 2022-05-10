package com.android.assignment.service.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @field:SerializedName("release_date")
    val release_date: String,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("tagline")
    val tagline: String,

    @field:SerializedName("title")
    val title: String
    )