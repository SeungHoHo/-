package fastcampus.aop.aop_part6_chapter01.di

import com.google.firebase.auth.FirebaseAuth
import fastcampus.aop.aop_part6_chapter01.data.TestOrderRepository
import fastcampus.aop.aop_part6_chapter01.data.TestRestaurantFoodRepository
import fastcampus.aop.aop_part6_chapter01.data.TestRestaurantRepository
import fastcampus.aop.aop_part6_chapter01.data.entity.LocationLatLngEntity
import fastcampus.aop.aop_part6_chapter01.data.respository.order.OrderRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.restaurant.RestaurantRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.restaurant.food.RestaurantFoodRepository
import fastcampus.aop.aop_part6_chapter01.screen.main.home.restaurant.RestaurantCategory
import fastcampus.aop.aop_part6_chapter01.screen.main.home.restaurant.RestaurantListViewModel
import fastcampus.aop.aop_part6_chapter01.screen.order.OrderMenuListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {

    viewModel { (restaurantCategory: RestaurantCategory, locationLatLngEntity: LocationLatLngEntity) ->
        RestaurantListViewModel(restaurantCategory, locationLatLngEntity, get())
    }

    viewModel { (firebaseAuth: FirebaseAuth) -> OrderMenuListViewModel(get(), get(), firebaseAuth) }

    single<RestaurantRepository> { TestRestaurantRepository() }

    single<RestaurantFoodRepository> { TestRestaurantFoodRepository() }

    single<OrderRepository> { TestOrderRepository() }

    single { FirebaseAuth.getInstance() }

}