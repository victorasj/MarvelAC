import com.google.gson.annotations.SerializedName

data class CharacterSerie (
    @SerializedName("id")
    var id: Long,
    @SerializedName("digitalId")
    var digitalId: Long,
    @SerializedName("title")
    var title: String,
    @SerializedName("isbn")
    var isbn: String,
    @SerializedName("pageCount ")
    var pageCount : Int,
    @SerializedName("thumbnail")
    var image: SerieImage?
)

data class SerieImage (
    @SerializedName("path")
    var path: String,
    @SerializedName("extension")
    var extension: String
)

data class CharacterSeriesDataWrapper (
    @SerializedName("code")
    var code: Int,
    @SerializedName("status")
    var status: String,
    @SerializedName("copyright")
    var copyright: String,
    @SerializedName("attributeText")
    var attributeText: String,
    @SerializedName("attributionHTML")
    var attributionHTML: String,
    @SerializedName("data")
    var data: CharacterSeriesDataContainer,
    @SerializedName("etag")
    var etag: String
)

data class CharacterSeriesDataContainer (
    @SerializedName("offset")
    var offset: Int,
    @SerializedName("limit")
    var limit: Int,
    @SerializedName("total")
    var total: Int,
    @SerializedName("count")
    var count: Int,
    @SerializedName("results")
    var results: Array<CharacterSerie>
)
