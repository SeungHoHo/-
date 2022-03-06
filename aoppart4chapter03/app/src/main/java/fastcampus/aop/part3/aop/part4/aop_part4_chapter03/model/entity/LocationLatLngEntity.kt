package fastcampus.aop.part3.aop.part4.aop_part4_chapter03.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class LocationLatLngEntity (
    val latitude: Float,
    val longitude: Float
): Parcelable
