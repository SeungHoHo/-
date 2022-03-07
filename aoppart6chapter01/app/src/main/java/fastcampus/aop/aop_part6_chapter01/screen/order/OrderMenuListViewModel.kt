package fastcampus.aop.aop_part6_chapter01.screen.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import fastcampus.aop.aop_part6_chapter01.R
import fastcampus.aop.aop_part6_chapter01.data.respository.order.DefaultOrderRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.order.OrderRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.restaurant.food.RestaurantFoodRepository
import fastcampus.aop.aop_part6_chapter01.model.CellType
import fastcampus.aop.aop_part6_chapter01.model.restaurant.food.FoodModel
import fastcampus.aop.aop_part6_chapter01.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OrderMenuListViewModel(
    private val restaurantFoodRepository: RestaurantFoodRepository,
    private val orderRepository: OrderRepository,
    private val firebaseAuth: FirebaseAuth
): BaseViewModel() {

    val orderMenuStateLiveData = MutableLiveData<OrderMenuState>(OrderMenuState.Uninitialized)

    override fun fetchData(): Job = viewModelScope.launch {
        orderMenuStateLiveData.value = OrderMenuState.Loading
        val foodMenuList = restaurantFoodRepository.getAllFoodMenuListInBasket()
        orderMenuStateLiveData.value = OrderMenuState.Success(
            foodMenuList.map {
                FoodModel(
                    id = it.hashCode().toLong(),
                    type = CellType.ORDER_FOOD_CELL,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    imageUrl = it.imageUrl,
                    restaurantId = it.restaurantId,
                    foodId = it.id,
                    restaurantTitle = it.restaurantTitle
                )
            }
        )
    }

    fun clearOrderMenu() = viewModelScope.launch {
        restaurantFoodRepository.clearFoodMenuListInBasket()
        fetchData()
    }

    fun removeOrderMenu(model: FoodModel) = viewModelScope.launch {
        restaurantFoodRepository.removeFoodMenuListInBasket(model.foodId)
        fetchData()
    }

    fun orderMenu() = viewModelScope.launch {
        val foodMenuList = restaurantFoodRepository.getAllFoodMenuListInBasket()
        if (foodMenuList.isNotEmpty()) {
            val restaurantId = foodMenuList.first().restaurantId
            val restaurantTitle = foodMenuList.first().restaurantTitle
            firebaseAuth.currentUser?.let { user ->
                when (val data = orderRepository.orderMenu(user.uid, restaurantId, foodMenuList, restaurantTitle)) {
                    is DefaultOrderRepository.Result.Success<*> -> {
                        restaurantFoodRepository.clearFoodMenuListInBasket()
                        orderMenuStateLiveData.value = OrderMenuState.Order
                    }
                    is DefaultOrderRepository.Result.Error -> {
                        orderMenuStateLiveData.value = OrderMenuState.Error(
                            R.string.request_error, data.e
                        )
                    }
                }
            } ?: kotlin.run {
                orderMenuStateLiveData.value = OrderMenuState.Error(
                    R.string.user_id_not_found, IllegalAccessException()
                )
            }
        }
    }
}