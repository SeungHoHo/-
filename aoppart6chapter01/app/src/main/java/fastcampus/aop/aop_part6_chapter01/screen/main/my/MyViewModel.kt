package fastcampus.aop.aop_part6_chapter01.screen.main.my

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import fastcampus.aop.aop_part6_chapter01.R
import fastcampus.aop.aop_part6_chapter01.data.entity.OrderEntity
import fastcampus.aop.aop_part6_chapter01.data.preference.AppPreferenceManager
import fastcampus.aop.aop_part6_chapter01.data.respository.order.DefaultOrderRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.order.OrderRepository
import fastcampus.aop.aop_part6_chapter01.data.respository.user.UserRepository
import fastcampus.aop.aop_part6_chapter01.model.restaurant.order.OrderModel
import fastcampus.aop.aop_part6_chapter01.screen.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel(
    private val appPreferenceManager: AppPreferenceManager,
    private val userRepository: UserRepository,
    private val orderRepository: OrderRepository
): BaseViewModel() {

    val myStateLiveData = MutableLiveData<MyState>(MyState.Uninitialized)

    override fun fetchData(): Job = viewModelScope.launch {
        myStateLiveData.value = MyState.Loading
        appPreferenceManager.getIdToken()?.let {
            myStateLiveData.value = MyState.Login(it)
        } ?: kotlin.run {
            myStateLiveData.value = MyState.Success.NotRegistered
        }
    }

    fun saveToken(idToken: String) = viewModelScope.launch{
        withContext(Dispatchers.IO) {
            appPreferenceManager.putIdToken(idToken)
            fetchData()
        }
    }

    fun setUserInfo(firebaseUser: FirebaseUser?) = viewModelScope.launch {
        firebaseUser?.let { user ->
            when (val orderMenuResult = orderRepository.getAllOrderMenus(user.uid)) {
                is DefaultOrderRepository.Result.Success<*> -> {
                    val orderList = orderMenuResult.data as List<OrderEntity>
                    myStateLiveData.value = MyState.Success.Registered(
                        userName = user.displayName ?: "익명",
                        profileImageUri = user.photoUrl,
                        orderList = orderList.map {
                            OrderModel(
                                id = it.hashCode().toLong(),
                                orderId = it.id,
                                userId = it.userId,
                                restaurantId = it.restaurantId,
                                foodMenuList = it.foodMenuList,
                                restaurantTitle = it.restaurantTitle
                            )
                        }
                    )
                }
                is DefaultOrderRepository.Result.Error -> {
                    myStateLiveData.value = MyState.Error(
                        R.string.request_error,
                        orderMenuResult.e
                    )
                }
            }
        } ?: kotlin.run {
            myStateLiveData.value = MyState.Success.NotRegistered
        }
    }

    fun signOut() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            appPreferenceManager.removeIdToken()
        }
        userRepository.deleteALlUserLikedRestaurant()
        fetchData()
    }
}