package com.example.marvelac.ui.common

import androidx.viewbinding.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*

fun createURLCharacter() : String {
    val public_key = "be8c856627468186e6cbdb2d680456d1"
    val private_key = "5c80fdde34561bc736abb4d67d5f3e6db2c237ca"
    val ts = timeStampCreate()
    val hashKey = hashcreate(ts, public_key, private_key)
    return "characters?ts=$ts&apikey=$public_key&hash=$hashKey"
}

fun createURLCharacter(type : String, characterId : Long) : String {
    val public_key = "be8c856627468186e6cbdb2d680456d1"
    val private_key = "5c80fdde34561bc736abb4d67d5f3e6db2c237ca"
    val ts = timeStampCreate()
    val hashKey = hashcreate(ts, public_key, private_key)
    return "characters/$characterId/$type?ts=$ts&apikey=$public_key&hash=$hashKey"
}

fun timeStampCreate() : String {
    val sdf = SimpleDateFormat("yyyyMMddHHmmSSsss")
    val dateObject = Date()
    val dateString = sdf.format(dateObject)
    return dateString
}

fun hashcreate(ts : String, public_key: String, private_key: String) : String {
    val stringAll = ts + private_key + public_key
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(stringAll.toByteArray())).toString(16).padStart(32, '0')
}
