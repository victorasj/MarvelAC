package com.example.marvelac.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "character")
data class CharacterDb (
    @PrimaryKey var id : Long,
    var name: String,
    var description: String,
    var imageURL: String
) : Parcelable
