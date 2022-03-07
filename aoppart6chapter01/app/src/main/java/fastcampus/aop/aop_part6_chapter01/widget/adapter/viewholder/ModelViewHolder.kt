package fastcampus.aop.aop_part6_chapter01.widget.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import fastcampus.aop.aop_part6_chapter01.model.Model
import fastcampus.aop.aop_part6_chapter01.screen.base.BaseViewModel
import fastcampus.aop.aop_part6_chapter01.util.provider.ResourcesProvider
import fastcampus.aop.aop_part6_chapter01.widget.adapter.listener.AdapterListener

abstract class ModelViewHolder<M: Model>(
    binding: ViewBinding,
    protected val viewModel: BaseViewModel,
    protected val resourcesProvider: ResourcesProvider
): RecyclerView.ViewHolder(binding.root) {

    abstract fun reset()

    open fun bindData(model: M) {
        reset()
    }

    abstract fun bindViews(model: M, adapterListener: AdapterListener)

}