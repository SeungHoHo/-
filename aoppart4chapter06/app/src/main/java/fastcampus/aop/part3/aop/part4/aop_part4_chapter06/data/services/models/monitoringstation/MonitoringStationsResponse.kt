package fastcampus.aop.part3.aop.part4.aop_part4_chapter06.data.services.models.monitoringstation


import com.google.gson.annotations.SerializedName

data class MonitoringStationsResponse(
    @SerializedName("response")
    val response: Response?
)