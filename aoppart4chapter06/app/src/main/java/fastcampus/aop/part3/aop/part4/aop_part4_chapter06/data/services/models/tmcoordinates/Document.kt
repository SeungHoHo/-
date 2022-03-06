package fastcampus.aop.part3.aop.part4.aop_part4_chapter06.data.services.models.tmcoordinates


import com.google.gson.annotations.SerializedName

data class Document(
    @SerializedName("x")
    val x: Double?,
    @SerializedName("y")
    val y: Double?
)