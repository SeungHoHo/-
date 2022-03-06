package fastcampus.aop.part3.aop.part4.aop_part4_chapter06.data.services.models.tmcoordinates


import com.google.gson.annotations.SerializedName

data class TmCoordinatesResponse(
    @SerializedName("documents")
    val documents: List<Document>?,
    @SerializedName("meta")
    val meta: Meta?
)