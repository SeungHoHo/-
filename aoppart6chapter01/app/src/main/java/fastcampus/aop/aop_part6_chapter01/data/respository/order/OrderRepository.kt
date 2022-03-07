
package fastcampus.aop.aop_part6_chapter01.data.respository.order

import fastcampus.aop.aop_part6_chapter01.data.entity.RestaurantFoodEntity

interface OrderRepository {

    suspend fun orderMenu(
        userId: String,
        restaurantId: Long,
        foodMenuList: List<RestaurantFoodEntity>,
        restaurantTitle: String
    ): DefaultOrderRepository.Result

    suspend fun getAllOrderMenus(
        userId: String
    ): DefaultOrderRepository.Result

}