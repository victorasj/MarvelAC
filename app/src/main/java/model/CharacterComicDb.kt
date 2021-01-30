package model

import com.google.gson.annotations.SerializedName
import com.victorasj.pruebatecnica.models.CharacterImage

data class CharacterComicDb (
    var name: String,
    var description: String,
    var imageURL: String,
)
