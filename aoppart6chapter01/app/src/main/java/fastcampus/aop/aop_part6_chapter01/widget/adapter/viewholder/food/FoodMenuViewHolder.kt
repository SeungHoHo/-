package fastcampus.aop.aop_part6_chapter01.widget.adapter.viewholder.food

import com.bumptech.glide.load.resource.bitmap.CenterCrop
import fastcampus.aop.aop_part6_chapter01.R
import fastcampus.aop.aop_part6_chapter01.databinding.ViewholderFoodMenuBinding
import fastcampus.aop.aop_part6_chapter01.extensions.clear
import fastcampus.aop.aop_part6_chapter01.extensions.load
import fastcampus.aop.aop_part6_chapter01.model.restaurant.food.FoodModel
import fastcampus.aop.aop_part6_chapter01.screen.base.BaseViewModel
import fastcampus.aop.aop_part6_chapter01.screen.main.home.restaurant.detail.menu.RestaurantMenuListViewModel
import fastcampus.aop.aop_part6_chapter01.util.provider.ResourcesProvider
import fastcampus.aop.aop_part6_chapter01.widget.adapter.listener.AdapterListener
import fastcampus.aop.aop_part6_chapter01.widget.adapter.listener.restaurant.FoodMenuListListener
import fastcampus.aop.aop_part6_chapter01.widget.adapter.viewholder.ModelViewHolder

class FoodMenuViewHolder(
    private val binding: ViewholderFoodMenuBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
): ModelViewHolder<FoodModel>(binding, viewModel, resourcesProvider) {

    override fun reset() = with(binding) {
        foodImage.clear()
    }

    override fun bindData(model: FoodModel) {
        super.bindData(model)
        with(binding) {
            foodImage.load(model.imageUrl, 24f, CenterCrop())
            foodTitleText.text = model.title
            foodDescriptionText.text = model.description
            priceText.text = resourcesProvider.getString(R.string.price, model.price)
        }
    }

    override fun bindViews(model: FoodModel, adapterListener: AdapterListener) {
        if (adapterListener is FoodMenuListListener) {
            binding.root.setOnClickListener {
                adapterListener.onClickItem(model)
            }
        }
    }

}