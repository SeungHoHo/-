package fastcampus.aop.part3.aop_part4_chapter01.service

import fastcampus.aop.part3.aop_part4_chapter01.dto.VideoDto
import retrofit2.Call
import retrofit2.http.GET

interface VideoService {

    @GET("/v3/dfbba762-08d1-4b29-8210-67900d78f08c")
    fun listVideos(): Call<VideoDto>
}