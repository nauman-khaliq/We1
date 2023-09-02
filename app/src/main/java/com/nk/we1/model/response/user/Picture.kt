/*
 * MIT License
 *
 * Copyright (c) 2022 Nauman Khaliq
 */
package com.nk.we1.model.response.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Picture (
    @Json(name = "large") var large: String?,
    @Json(name = "medium") var medium: String?,
    @Json(name = "thumbnail") var thumbnail: String?,
)