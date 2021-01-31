package com.example.marvelac.ui.common

import java.math.BigInteger
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*

fun timeStampCreate() : String {
    var sdf = SimpleDateFormat("yyyyMMddHHmmSSsss")
    var dateObject = Date()
    var dateString = sdf.format(dateObject)
    return dateString
}

fun hashcreate(ts : String, public_key: String, private_key: String) : String {
    var stringAll = ts + private_key + public_key
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(stringAll.toByteArray())).toString(16).padStart(32, '0')
}
