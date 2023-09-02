/*
 * MIT License
 *
 * Copyright (c) 2022 Nauman Khaliq
 */
package com.nk.we1.model.response.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Name (
    @Json(name = "title") var title: String?,
    @Json(name = "first") var first: String?,
    @Json(name = "last") var last: String?,
)