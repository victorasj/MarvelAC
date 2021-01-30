package model

import com.google.gson.annotations.SerializedName
import com.victorasj.pruebatecnica.models.CharacterImage

data class CharacterSerieDb (
    var name: String,
    var description: String,
    var imageURL: String,
)
