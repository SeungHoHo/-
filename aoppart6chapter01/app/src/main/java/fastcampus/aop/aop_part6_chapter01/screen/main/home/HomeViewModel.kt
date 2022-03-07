package fastcampus.aop.aop_part6_chapter01.screen.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fastcampus.aop.aop_part6_chapter01.R
import fastcampus.aop.aop_part6_chapter01.data.entity.LocationLatLngEntity
import fastcampus.aop.aop_part6_chapter01.data.entity.MapSearchInfoEntity
import fastcampus.aop.aop_part6_chapter01.data.entity.RestaurantFoodEntity
import fastcampus.aop.aop_part6_chapter01.data.respository.map.MapRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.restaurant.food.RestaurantFoodRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.user.UserRepository
import fastcampus.aop.aop_part6_chapter01.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val mapRepository: MapRepository,
    private val userRepository: UserRepository,
    private val restaurantFoodRepository: RestaurantFoodRepository
): BaseViewModel() {

    companion object {
        const val  MY_LOCATION_LEY = "MyLocation"
    }

    val homeStateLiveData = MutableLiveData<HomeState>(HomeState.Uninitialized)

    val foodMenuBasketLiveData = MutableLiveData<List<RestaurantFoodEntity>>()

    fun loadReverseGeoInformation(
        locationLatLngEntity: LocationLatLngEntity
    ) = viewModelScope.launch {
        homeStateLiveData.value = HomeState.Loading
        val userLocation = userRepository.getUserLocation()
        val currentLocation = userLocation ?: locationLatLngEntity

        val addressInfo = mapRepository.getReverseGeoInformation(currentLocation)
        addressInfo?.let { info ->
            homeStateLiveData.value = HomeState.Success(
               mapSearchInfo = info.toSearchInfoEntity(locationLatLngEntity),
                isLocationSame = currentLocation == locationLatLngEntity
            )
        } ?: kotlin.run {
            homeStateLiveData.value = HomeState.Error(
                R.string.can_not_load_address_info
            )
        }
    }

    fun getMapSearchInfo(): MapSearchInfoEntity? {
        when (val data = homeStateLiveData.value) {
            is HomeState.Success -> {
                return data.mapSearchInfo
            }
        }
        return null
    }

    fun checkMyBasket() = viewModelScope.launch {
       foodMenuBasketLiveData.value = restaurantFoodRepository.getAllFoodMenuListInBasket()
    }
}