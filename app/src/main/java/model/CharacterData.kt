package com.victorasj.pruebatecnica.models

import com.google.gson.annotations.SerializedName

data class CharacterData (
    @SerializedName("id")
    var id: Long,
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("thumbnail")
    var image: CharacterImage?
)

data class CharacterImage (
    @SerializedName("path")
    var path: String,
    @SerializedName("extension")
    var extension: String
)

data class CharacterDataContainer (
    @SerializedName("total")
    var total: Int,
    @SerializedName("count")
    var count: Int,
    @SerializedName("results")
    var results: Array<CharacterData>
)

data class CharacterDataWrapper (
    @SerializedName("code")
    var code: Int,
    @SerializedName("status")
    var status: String,
    @SerializedName("data")
    var data: CharacterDataContainer
)
