package fastcampus.aop.part3.aop.part4.aop_part4_chapter03.model.entity

import android.os.Parcelable
import fastcampus.aop.part3.aop.part4.aop_part4_chapter03.model.entity.LocationLatLngEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResultEntity(
    val fullAdress: String,
    val name: String,
    val locationLatLng: LocationLatLngEntity
): Parcelable
