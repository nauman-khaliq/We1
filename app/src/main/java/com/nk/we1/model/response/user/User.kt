/*
 * MIT License
 *
 * Copyright (c) 2022 Nauman Khaliq
 */
package com.nk.we1.model.response.user

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nk.we1.model.response.user.User.Companion.TABLE_NAME
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = TABLE_NAME)
@JsonClass(generateAdapter = true)
data class User(
    @PrimaryKey(autoGenerate = true)
    var primaryKeyId: Int?,
    @Embedded
    @Json(name = "name") var name: Name?,
    @Json(name = "email") var email: String?,
    @Json(name = "cell") var cell: String?,
    @Embedded
    @Json(name = "location") var location: Location?,
    @Embedded
    @Json(name = "picture") var picture: Picture?,
    @Json(name = "nat") var nat: String?,
    var distance: String?
) {
    companion object {
        const val TABLE_NAME = "users"
    }
}












//
///**
// * [NameJsonConverter] will be to convert name to json and back for room database storage
// */
//class NameJsonConverter {
//    /**
//     * Converts name of type [Name] to json [String]
//     * @param name of type [Name]
//     * @return [String]
//     */
//    @TypeConverter
//    fun fromMenu(name: Name?): String? {
//        return Moshi.Builder().build().adapter(Name::class.java).toJson(name)
//    }
//
//    /**
//     * Converts nameJson of type [String] to name of type [Name]
//     * @param nameJson of type [String]
//     * @return [Name]
//     */
//    @TypeConverter
//    fun toMenu(nameJson: String?): Name? {
//        return Moshi.Builder().build().adapter(Name::class.java).fromJson(nameJson ?: "")
//    }
//}
//
//
///**
// * [LocationJsonConverter] will be to convert name to json and back for room database storage
// */
//class LocationJsonConverter {
//    /**
//     * Converts name of type [Location] to json [String]
//     * @param location of type [Location]
//     * @return [String]
//     */
//    @TypeConverter
//    fun fromLocation(location: Location?): String? {
//        return Moshi.Builder().build().adapter(Location::class.java).toJson(location)
//    }
//
//    /**
//     * Converts locationJson of type [String] to location of type [Location]
//     * @param locationJson of type [String]
//     * @return [Location]
//     */
//    @TypeConverter
//    fun toLocation(locationJson: String?): Location? {
//        return Moshi.Builder().build().adapter(Location::class.java).fromJson(locationJson ?: "")
//    }
//}
//
///**
// * [PictureJsonConverter] will be to convert name to json and back for room database storage
// */
//class PictureJsonConverter {
//    /**
//     * Converts name of type [Picture] to json [String]
//     * @param picture of type [Picture]
//     * @return [String]
//     */
//    @TypeConverter
//    fun fromPicture(picture: Picture?): String? {
//        return Moshi.Builder().build().adapter(Picture::class.java).toJson(picture)
//    }
//
//    /**
//     * Converts pictureJson of type [String] to picture of type [Picture]
//     * @param pictureJson of type [String]
//     * @return [Picture]
//     */
//    @TypeConverter
//    fun toLocation(pictureJson: String?): Picture? {
//        return Moshi.Builder().build().adapter(Picture::class.java).fromJson(pictureJson ?: "")
//    }
//}
//
//







