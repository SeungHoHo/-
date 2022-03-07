package fastcampus.aop.aop_part6_chapter01.data

import fastcampus.aop.aop_part6_chapter01.data.entity.OrderEntity
import fastcampus.aop.aop_part6_chapter01.data.entity.RestaurantFoodEntity
import fastcampus.aop.aop_part6_chapter01.data.respository.order.DefaultOrderRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.order.OrderRepository

class TestOrderRepository: OrderRepository {

    private var orderEntities = mutableListOf<OrderEntity>()

    override suspend fun orderMenu(
        userId: String,
        restaurantId: Long,
        foodMenuList: List<RestaurantFoodEntity>,
        restaurantTitle: String
    ): DefaultOrderRepository.Result {
        orderEntities.add(
            OrderEntity(
                id = orderEntities.size.toString(),
                userId = userId,
                restaurantId = restaurantId,
                foodMenuList = foodMenuList.map { it.copy() },
                restaurantTitle = restaurantTitle
            )
        )
        return DefaultOrderRepository.Result.Success<Any>()
    }

    override suspend fun getAllOrderMenus(userId: String): DefaultOrderRepository.Result {
        return DefaultOrderRepository.Result.Success(orderEntities)
    }

}