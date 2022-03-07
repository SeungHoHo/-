package fastcampus.aop.aop_part6_chapter01.data.entity

import android.os.Parcelable
import androidx.room.PrimaryKey
import fastcampus.aop.aop_part6_chapter01.data.entity.Entity
import androidx.room.TypeConverters
import fastcampus.aop.aop_part6_chapter01.screen.main.home.restaurant.RestaurantCategory
import fastcampus.aop.aop_part6_chapter01.util.convertor.RoomTypeConverters
import kotlinx.parcelize.Parcelize

@Parcelize
@androidx.room.Entity
@TypeConverters(RoomTypeConverters::class)
data class RestaurantEntity(
    override val id: Long,
    val restaurantInfoId: Long,
    val restaurantCategory: RestaurantCategory,
    @PrimaryKey val restaurantTitle: String,
    val restaurantImageUrl: String,
    val grade: Float,
    val reviewCount: Int,
    val deliveryTimeRange: Pair<Int, Int>,
    val deliveryTipRange: Pair<Int, Int>,
    val restaurantTelNumber: String?
): Entity, Parcelable