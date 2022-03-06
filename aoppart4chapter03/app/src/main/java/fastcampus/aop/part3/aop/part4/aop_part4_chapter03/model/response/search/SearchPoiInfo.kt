package fastcampus.aop.part3.aop.part4.aop_part4_chapter03.model.response.search

data class SearchPoiInfo(
    val totalCount: String,
    val count: String,
    val page: String,
    val pois: Pois
)