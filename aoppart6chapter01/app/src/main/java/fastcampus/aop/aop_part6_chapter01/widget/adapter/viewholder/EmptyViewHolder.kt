package fastcampus.aop.aop_part6_chapter01.widget.adapter.viewholder

import fastcampus.aop.aop_part6_chapter01.databinding.ViewholderEmptyBinding
import fastcampus.aop.aop_part6_chapter01.model.Model
import fastcampus.aop.aop_part6_chapter01.screen.base.BaseViewModel
import fastcampus.aop.aop_part6_chapter01.util.provider.ResourcesProvider
import fastcampus.aop.aop_part6_chapter01.widget.adapter.listener.AdapterListener

class EmptyViewHolder(
    private val binding: ViewholderEmptyBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
): ModelViewHolder<Model>(binding, viewModel, resourcesProvider) {

    override fun reset() = Unit

    override fun bindViews(model: Model, adapterListener: AdapterListener) = Unit

}