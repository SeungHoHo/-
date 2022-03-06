package fastcampus.aop.part3.aop.part4.aop_part4_chapter06.data.services.models.airquality


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("body")
    val body: Body?,
    @SerializedName("header")
    val header: Header?
)