package com.nk.we1.utils

import android.location.Location
import kotlin.random.Random

object LocationUtil {
    /**
     * Generates random Latitude Longitude and returns a location
     */
    fun generateRandomLocation(): Location {
        val latitude = Random.nextDouble((-90).toDouble(),(91).toDouble())
        val longitude = Random.nextDouble((-180).toDouble(),(181).toDouble())
        val locRand  = Location("")
        locRand.latitude = latitude
        locRand.longitude = longitude
        return locRand
    }
}