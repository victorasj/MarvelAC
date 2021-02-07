package com.example.marvelac.data

import com.google.gson.annotations.SerializedName

data class CharacterComicsDataWrapper (
        @SerializedName("code") var code: Int,
        @SerializedName("status") var status: String,
        @SerializedName("copyright") var copyright: String,
        @SerializedName("attributeText") var attributeText: String,
        @SerializedName("attributionHTML") var attributionHTML: String,
        @SerializedName("data") var data: CharacterComicsDataContainer,
        @SerializedName("etag") var etag: String
)

data class CharacterComicsDataContainer (
        @SerializedName("offset") var offset: Int,
        @SerializedName("limit") var limit: Int,
        @SerializedName("total") var total: Int,
        @SerializedName("count") var count: Int,
        @SerializedName("results") var results: Array<CharacterComic>
)

data class CharacterComic (
        @SerializedName("id") var id: Long,
        @SerializedName("digitalId") var digitalId: Long,
        @SerializedName("title") var title: String,
        @SerializedName("isbn") var isbn: String,
        @SerializedName("pageCount ") var pageCount : Int,
        @SerializedName("thumbnail") var image: ComicImage?
)


data class ComicImage (
        @SerializedName("path") var path: String,
        @SerializedName("extension") var extension: String
)