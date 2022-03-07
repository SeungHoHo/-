package fastcampus.aop.aop_part5_chapter06_repeat.di

import android.app.Activity
import fastcampus.aop.aop_part5_chapter06_repeat.BuildConfig
import fastcampus.aop.aop_part5_chapter06_repeat.data.api.SweetTrackerApi
import fastcampus.aop.aop_part5_chapter06_repeat.data.api.Url
import fastcampus.aop.aop_part5_chapter06_repeat.data.db.AppDatabase
import fastcampus.aop.aop_part5_chapter06_repeat.data.entity.TrackingInformation
import fastcampus.aop.aop_part5_chapter06_repeat.data.entity.TrackingItem
import fastcampus.aop.aop_part5_chapter06_repeat.data.preference.PreferenceManager
import fastcampus.aop.aop_part5_chapter06_repeat.data.preference.SharedPreferenceManager
import fastcampus.aop.aop_part5_chapter06_repeat.data.repository.ShippingCompanyRepository
import fastcampus.aop.aop_part5_chapter06_repeat.data.repository.ShippingCompanyRepositoryImpl
import fastcampus.aop.aop_part5_chapter06_repeat.data.repository.TrackingItemRepository
import fastcampus.aop.aop_part5_chapter06_repeat.data.repository.TrackingItemRepositoryImpl
import fastcampus.aop.aop_part5_chapter06_repeat.presentation.addtrackingItem.AddTrackingItemFragment
import fastcampus.aop.aop_part5_chapter06_repeat.presentation.addtrackingItem.AddTrackingItemPresenter
import fastcampus.aop.aop_part5_chapter06_repeat.presentation.addtrackingItem.AddTrackingItemsContract
import fastcampus.aop.aop_part5_chapter06_repeat.presentation.trackinghistory.TrackingHistoryContract
import fastcampus.aop.aop_part5_chapter06_repeat.presentation.trackinghistory.TrackingHistoryFragment
import fastcampus.aop.aop_part5_chapter06_repeat.presentation.trackinghistory.TrackingHistoryPresenter
import fastcampus.aop.aop_part5_chapter06_repeat.presentation.trackingitems.TrackingItemsContract
import fastcampus.aop.aop_part5_chapter06_repeat.presentation.trackingitems.TrackingItemsFragment
import fastcampus.aop.aop_part5_chapter06_repeat.presentation.trackingitems.TrackingItemsPresenter
import fastcampus.aop.aop_part5_chapter06_repeat.work.AppWorkerFactory
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val appModule = module {

    single { Dispatchers.IO }

    // Database
    single { AppDatabase.build(androidApplication()) }
    single { get<AppDatabase>().trackingItemDao() }
    single { get<AppDatabase>().shippingCompanyDao() }

    // Api
    single {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .build()
    }
    single<SweetTrackerApi> {
        Retrofit.Builder().baseUrl(Url.SWEET_TRACKER_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create()
    }

    // Preference
    single { androidContext().getSharedPreferences("preference", Activity.MODE_PRIVATE) }
    single<PreferenceManager> { SharedPreferenceManager(get()) }

    // Repository
    //    single<TrackingItemRepository> { TrackingItemRepositoryStub() }
    single<TrackingItemRepository> { TrackingItemRepositoryImpl(get(), get(), get()) }
    single<ShippingCompanyRepository> { ShippingCompanyRepositoryImpl(get(), get(), get(), get()) }

    // Work
    single { AppWorkerFactory(get(), get()) }

    // Presentation
    scope<TrackingItemsFragment> {
        scoped<TrackingItemsContract.Presenter> { TrackingItemsPresenter(getSource(), get()) }
    }
    scope<AddTrackingItemFragment> {
        scoped<AddTrackingItemsContract.Presenter> {
            AddTrackingItemPresenter(getSource(), get(), get())
        }
    }
    scope<TrackingHistoryFragment> {
        scoped<TrackingHistoryContract.Presenter> { (trackingItem: TrackingItem, trackingInformation: TrackingInformation) ->
            TrackingHistoryPresenter(getSource(), get(), trackingItem, trackingInformation)
        }
    }
}