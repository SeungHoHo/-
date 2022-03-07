package fastcampus.aop.aop_part6_chapter01.screen.main.home.restaurant.detail.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fastcampus.aop.aop_part6_chapter01.data.entity.RestaurantFoodEntity
import fastcampus.aop.aop_part6_chapter01.data.respository.restaurant.food.RestaurantFoodRepository
import fastcampus.aop.aop_part6_chapter01.model.restaurant.food.FoodModel
import fastcampus.aop.aop_part6_chapter01.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RestaurantMenuListViewModel(
    private val restaurantId: Long,
    private val foodEntityList: List<RestaurantFoodEntity>,
    private val restaurantFoodRespository: RestaurantFoodRepository
): BaseViewModel() {

    val restaurantFoodListLiveData = MutableLiveData<List<FoodModel>>()

    val menuBasketLiveData = MutableLiveData<RestaurantFoodEntity>()

    val isClearNeedInBasketLiveData = MutableLiveData<Pair<Boolean, () -> Unit>>()

    override fun fetchData(): Job = viewModelScope.launch {
        restaurantFoodListLiveData.value = foodEntityList.map {
            FoodModel(
                id =  it.hashCode().toLong(),
                title = it.title,
                description = it.description,
                price = it.price,
                imageUrl = it.imageUrl,
                restaurantId = restaurantId,
                foodId = it.id,
                restaurantTitle = it.restaurantTitle
            )
        }
    }

    fun insertMenuInBasket(foodModel: FoodModel) = viewModelScope.launch {
        val restaurantMenuListInBasket = restaurantFoodRespository.getFoodMenuListInBasket(restaurantId)
        val foodMenuEntity = foodModel.toEntity(restaurantMenuListInBasket.size)
        val anotherRestaurantMenuListInBasket =
            restaurantFoodRespository.getAllFoodMenuListInBasket().filter { it.restaurantId != restaurantId }
        if (anotherRestaurantMenuListInBasket.isNotEmpty()) {
            isClearNeedInBasketLiveData.value = Pair(true, { clearMenuAndInsertNewMenuInBasket(foodMenuEntity) })
        } else {
            restaurantFoodRespository.insertFoodMenuInBasket(foodMenuEntity)
            menuBasketLiveData.value = foodMenuEntity
        }
    }

    private fun clearMenuAndInsertNewMenuInBasket(foodMenuEntity: RestaurantFoodEntity) = viewModelScope.launch {
        restaurantFoodRespository.clearFoodMenuListInBasket()
        restaurantFoodRespository.insertFoodMenuInBasket(foodMenuEntity)
        menuBasketLiveData.value = foodMenuEntity
    }

}