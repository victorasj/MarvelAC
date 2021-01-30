package model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.victorasj.pruebatecnica.models.CharacterImage
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterDb (var name: String, var description: String, var imageURL: String) : Parcelable
