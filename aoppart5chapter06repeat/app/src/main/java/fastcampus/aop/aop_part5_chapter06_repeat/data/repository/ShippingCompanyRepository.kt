package fastcampus.aop.aop_part5_chapter06_repeat.data.repository

import fastcampus.aop.aop_part5_chapter06_repeat.data.entity.ShippingCompany

interface ShippingCompanyRepository {

    suspend fun getShippingCompanies(): List<ShippingCompany>

    suspend fun getRecommendShippingCompany(invoice: String): ShippingCompany?
}