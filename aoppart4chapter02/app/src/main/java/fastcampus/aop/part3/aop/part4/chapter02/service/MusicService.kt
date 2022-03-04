package fastcampus.aop.part3.aop.part4.chapter02.service

import retrofit2.Call
import retrofit2.http.GET

interface MusicService {

    @GET("/v3/dfc443c3-9f30-494d-b12d-9862f56b0a6d")
    fun listMusics() : Call<MusicDto>
}