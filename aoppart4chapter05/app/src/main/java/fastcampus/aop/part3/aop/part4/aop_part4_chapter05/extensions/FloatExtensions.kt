package fastcampus.aop.part3.aop.part4.aop_part4_chapter05.extensions

import android.content.res.Resources

internal fun Float.fromDpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}