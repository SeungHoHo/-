package fastcampus.aop.aop_part5_chapter06_repeat.data.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(primaryKeys = ["invoice", "code"])
data class TrackingItem(
    val invoice: String,
    @Embedded val company: ShippingCompany
) : Parcelable