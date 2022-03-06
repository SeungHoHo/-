package fastcampus.aop.part3.aop.part4.aop_part4_chapter06.data.services.models.monitoringstation


import com.google.gson.annotations.SerializedName

data class Header(
    @SerializedName("resultCode")
    val resultCode: String?,
    @SerializedName("resultMsg")
    val resultMsg: String?
)