package fastcampus.aop.part3.part3_charpter07

import retrofit2.Call
import retrofit2.http.GET

interface HouseService {
    @GET("/v3/367b3017-5585-4fd0-95c4-78956f7691a9")
    fun getHouseList(): Call<HouseDto>
}