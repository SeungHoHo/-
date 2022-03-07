package fastcampus.aop.aop_part5_chapter06_repeat.presentation

interface BaseView<PresenterT : BasePresenter> {

    val presenter: PresenterT
}