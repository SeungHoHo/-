package fastcampus.aop.aop_part6_chapter01.screen.main.home.restaurant.detail.review

import androidx.core.os.bundleOf
import fastcampus.aop.aop_part6_chapter01.databinding.FragmentListBinding
import fastcampus.aop.aop_part6_chapter01.model.restaurant.review.RestaurantReviewModel
import fastcampus.aop.aop_part6_chapter01.screen.base.BaseFragment
import fastcampus.aop.aop_part6_chapter01.util.provider.ResourcesProvider
import fastcampus.aop.aop_part6_chapter01.widget.adapter.ModelRecyclerAdapter
import fastcampus.aop.aop_part6_chapter01.widget.adapter.listener.AdapterListener
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RestaurantReviewListFragment: BaseFragment<RestaurantReviewListViewModel, FragmentListBinding>() {

    override fun getViewBinding(): FragmentListBinding = FragmentListBinding.inflate(layoutInflater)

    override val viewModel by viewModel<RestaurantReviewListViewModel> {
        parametersOf(
            arguments?.getString(RESTAURANT_TITLE_KEY)
        )
    }

    private val resourcesProvider by inject<ResourcesProvider>()

    private val adapter by lazy {
        ModelRecyclerAdapter<RestaurantReviewModel, RestaurantReviewListViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            adapterListener = object : AdapterListener { }
            )
        }

    override fun initViews() {
        binding.recyclerView.adapter = adapter
    }

    override fun observeData() = viewModel.reviewStateLiveData.observe(viewLifecycleOwner) {
        when (it) {
            is RestaurantReviewState.Success -> {
                handleSuccess(it)
            }
        }
    }

    private fun handleSuccess(state: RestaurantReviewState.Success) {
        adapter.submitList(state.reviewList)
    }

    companion object {

        const val RESTAURANT_TITLE_KEY = "restaurantTitle"

        fun newInstance(restaurantTitle: String): RestaurantReviewListFragment {
            val bundle = bundleOf(
                RESTAURANT_TITLE_KEY to restaurantTitle
            )
            return RestaurantReviewListFragment().apply {
                arguments = bundle
            }
        }
    }


}