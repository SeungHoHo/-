package fastcampus.aop.aop_part6_chapter01.widget.adapter.viewholder.review

import androidx.core.view.isGone
import androidx.core.view.isVisible
import fastcampus.aop.aop_part6_chapter01.databinding.ViewholderRestaurantReviewBinding
import fastcampus.aop.aop_part6_chapter01.extensions.clear
import fastcampus.aop.aop_part6_chapter01.extensions.load
import fastcampus.aop.aop_part6_chapter01.model.restaurant.review.RestaurantReviewModel
import fastcampus.aop.aop_part6_chapter01.screen.base.BaseViewModel
import fastcampus.aop.aop_part6_chapter01.util.provider.ResourcesProvider
import fastcampus.aop.aop_part6_chapter01.widget.adapter.listener.AdapterListener
import fastcampus.aop.aop_part6_chapter01.widget.adapter.viewholder.ModelViewHolder

class RestaurantReviewViewHolder(
    private val binding: ViewholderRestaurantReviewBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
) : ModelViewHolder<RestaurantReviewModel>(binding, viewModel, resourcesProvider) {

    override fun reset() = with(binding) {
        reviewThumbnailImage.clear()
        reviewThumbnailImage.isGone = true
    }

    override fun bindData(model: RestaurantReviewModel) {
        super.bindData(model)
        with(binding) {
            if (model.thumbnailImageUri != null) {
                reviewThumbnailImage.isVisible = true
                reviewThumbnailImage.load(model.thumbnailImageUri.toString(), 24f)
            } else {
                reviewThumbnailImage.isGone = true
            }
            reviewTitleText.text = model.title
            reviewText.text = model.description
            ratingBar.rating = model.grade
        }
    }

    override fun bindViews(model: RestaurantReviewModel, adapterListener: AdapterListener) = Unit

}