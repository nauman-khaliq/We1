/*
 * MIT License
 *
 * Copyright (c) 2022 Nauman Khaliq
 */
package com.nk.we1.model.response.user

import androidx.room.Embedded
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location (
    @Embedded(prefix = "st")
    @Json(name = "street") var street: Street?,
    @Json(name = "city") var city: String?,
    @Json(name = "state") var state: String?,
    @Json(name = "country") var country: String?,
    @Json(name = "postcode") var postCode: String?,
    @Embedded(prefix = "cr")
    @Json(name = "coordinates") var coordinates: Coordinate?,
    @Embedded(prefix = "tz")
    @Json(name = "timezone") var timeZone: TimeZone?,
)

