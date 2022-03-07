package fastcampus.aop.aop_part6_chapter01.widget.adapter.listener.order

import fastcampus.aop.aop_part6_chapter01.widget.adapter.listener.AdapterListener

interface OrderListListener: AdapterListener {

    fun writeRestaurantReview(orderId: String, restaurantTitle: String)

}