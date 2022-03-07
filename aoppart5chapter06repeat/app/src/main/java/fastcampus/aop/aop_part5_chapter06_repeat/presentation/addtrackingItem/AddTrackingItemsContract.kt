package fastcampus.aop.aop_part5_chapter06_repeat.presentation.addtrackingItem

import fastcampus.aop.aop_part5_chapter06_repeat.data.entity.ShippingCompany
import fastcampus.aop.aop_part5_chapter06_repeat.presentation.BasePresenter
import fastcampus.aop.aop_part5_chapter06_repeat.presentation.BaseView

class AddTrackingItemsContract {

    interface View : BaseView<Presenter> {

        fun showShippingCompaniesLoadingIndicator()

        fun hideShippingCompaniesLoadingIndicator()

        fun showSaveTrackingItemIndicator()

        fun hideSaveTrackingItemIndicator()

        fun showRecommendCompanyLoadingIndicator()

        fun hideRecommendCompanyLoadingIndicator()

        fun showCompanies(companies: List<ShippingCompany>)

        fun showRecommendCompany(company: ShippingCompany)

        fun enableSaveButton()

        fun disableSaveButton()

        fun showErrorToast(message: String)

        fun finish()
    }

    interface Presenter : BasePresenter {

        var invoice: String?
        var shippingCompanies: List<ShippingCompany>?
        var selectedShippingCompany: ShippingCompany?

        fun fetchShippingCompanies()

        fun fetchRecommendShippingCompany()

        fun changeSelectedShippingCompany(companyName: String)

        fun changeShippingInvoice(invoice: String)

        fun saveTrackingItem()
    }
}