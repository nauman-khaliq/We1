/*
 * MIT License
 *
 * Copyright (c) 2022 Nauman Khaliq
 */
package com.nk.we1.model.response.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Street (
    @Json(name = "name") var name: String?,
    @Json(name = "number") var number: String?,
)