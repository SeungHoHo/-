package fastcampus.aop.aop_part6_chapter01.data.respository.restaurant

import fastcampus.aop.aop_part6_chapter01.data.entity.LocationLatLngEntity
import fastcampus.aop.aop_part6_chapter01.data.entity.RestaurantEntity
import fastcampus.aop.aop_part6_chapter01.screen.main.home.restaurant.RestaurantCategory

interface RestaurantRepository {

    suspend fun getList(
        restaurantCategory: RestaurantCategory,
        locationLatLngEntity: LocationLatLngEntity
    ): List<RestaurantEntity>

}