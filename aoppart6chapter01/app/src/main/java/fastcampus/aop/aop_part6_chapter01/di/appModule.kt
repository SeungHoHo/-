package fastcampus.aop.aop_part6_chapter01.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import fastcampus.aop.aop_part6_chapter01.data.entity.LocationLatLngEntity
import fastcampus.aop.aop_part6_chapter01.data.entity.MapSearchInfoEntity
import fastcampus.aop.aop_part6_chapter01.data.entity.RestaurantEntity
import fastcampus.aop.aop_part6_chapter01.data.entity.RestaurantFoodEntity
import fastcampus.aop.aop_part6_chapter01.data.preference.AppPreferenceManager
import fastcampus.aop.aop_part6_chapter01.data.respository.map.DefaultMapRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.map.MapRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.order.DefaultOrderRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.order.OrderRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.restaurant.DefaultRestaurantRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.restaurant.RestaurantRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.restaurant.food.DefaultRestaurantFoodRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.restaurant.food.RestaurantFoodRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.restaurant.review.DefaultRestaurantReviewRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.restaurant.review.RestaurantReviewRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.user.DefaultUserRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.user.UserRepository
import fastcampus.aop.aop_part6_chapter01.screen.main.home.HomeViewModel
import fastcampus.aop.aop_part6_chapter01.screen.main.home.restaurant.RestaurantCategory
import fastcampus.aop.aop_part6_chapter01.screen.main.home.restaurant.RestaurantListViewModel
import fastcampus.aop.aop_part6_chapter01.screen.main.home.restaurant.detail.RestaurantDetailViewModel
import fastcampus.aop.aop_part6_chapter01.screen.main.home.restaurant.detail.menu.RestaurantMenuListViewModel
import fastcampus.aop.aop_part6_chapter01.screen.main.home.restaurant.detail.review.RestaurantReviewListViewModel
import fastcampus.aop.aop_part6_chapter01.screen.main.like.RestaurantLikeListViewModel
import fastcampus.aop.aop_part6_chapter01.screen.main.my.MyViewModel
import fastcampus.aop.aop_part6_chapter01.screen.mylocation.MyLocationViewModel
import fastcampus.aop.aop_part6_chapter01.screen.order.OrderMenuListViewModel
import fastcampus.aop.aop_part6_chapter01.screen.review.gallery.GalleryPhotoRepository
import fastcampus.aop.aop_part6_chapter01.screen.review.gallery.GalleryViewModel
import fastcampus.aop.aop_part6_chapter01.util.event.MenuChangeEventBus
import fastcampus.aop.aop_part6_chapter01.util.provider.DefaultResourcesProvider
import fastcampus.aop.aop_part6_chapter01.util.provider.ResourcesProvider
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { MyViewModel(get(), get(), get()) }
    viewModel { RestaurantLikeListViewModel(get()) }

    viewModel { (restaurantCategory: RestaurantCategory, locationLatLng: LocationLatLngEntity) ->
        RestaurantListViewModel( restaurantCategory, locationLatLng, get() ) }
    viewModel { (mapSearchInfoEntity: MapSearchInfoEntity) -> MyLocationViewModel(mapSearchInfoEntity, get(), get()) }

    viewModel { (restaurantEntity: RestaurantEntity) -> RestaurantDetailViewModel(restaurantEntity, get(), get()) }

    viewModel { (restaurantId: Long, restaurantFoodList: List<RestaurantFoodEntity>) ->
        RestaurantMenuListViewModel(restaurantId, restaurantFoodList, get())
    }
    viewModel { (restaurantTitle: String) -> RestaurantReviewListViewModel(restaurantTitle, get()) }
    viewModel { OrderMenuListViewModel(get(), get(), get()) }

    viewModel { GalleryViewModel(get()) }

    single<RestaurantRepository> { DefaultRestaurantRepository(get(), get(), get()) }
    single<MapRepository> { DefaultMapRepository(get(), get()) }
    single<UserRepository> { DefaultUserRepository(get(), get(), get())  }
    single<RestaurantFoodRepository> { DefaultRestaurantFoodRepository(get(), get(), get())}
    single<RestaurantReviewRepository> { DefaultRestaurantReviewRepository(get(), get())}
    single<OrderRepository> { DefaultOrderRepository(get(), get())  }
    single { GalleryPhotoRepository(androidApplication()) }

    single { provideGsonConvertFactory() }
    single { buildOkHttpClient() }

    single(named("map")) { provideMapRetrofit(get(), get()) }
    single(named("food")) { provideFoodRetrofit(get(), get()) }

    single { provideMapApiService(get(qualifier = named("map"))) }
    single { provideFoodApiService(get(qualifier = named("food"))) }

    single { provideDB(androidApplication()) }
    single { provideLocationDao(get()) }
    single { provideRestaurantDao(get()) }
    single { provideFoodMenuBasketDao(get()) }

    single<ResourcesProvider> { DefaultResourcesProvider(androidApplication()) }
    single { AppPreferenceManager(androidApplication()) }

    single { Dispatchers.IO }
    single { Dispatchers.Main }

    single { MenuChangeEventBus() }

    single { Firebase.firestore }
    single { FirebaseAuth.getInstance() }
    single { Firebase.storage }
}