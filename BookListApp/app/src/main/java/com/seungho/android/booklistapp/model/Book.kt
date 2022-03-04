package com.seungho.android.booklistapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book (
    @SerializedName("itemId") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("author") val author: String,
    @SerializedName("priceSales") val priceSales: String,
    @SerializedName("coverSmallUrl") val coverSmallUrl: String,
    @SerializedName("saleStatus") val saleStatus: String,
    @SerializedName("publisher") val publisher: String

): Parcelable