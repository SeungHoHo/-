package fastcampus.aop.aop_part6_chapter01.widget.adapter.listener.order

import fastcampus.aop.aop_part6_chapter01.model.restaurant.food.FoodModel
import fastcampus.aop.aop_part6_chapter01.widget.adapter.listener.AdapterListener

interface OrderMenuListListener: AdapterListener {

    fun onRemoveItem(foodModel: FoodModel)

}