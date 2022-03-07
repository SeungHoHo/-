package fastcampus.aop.aop_part6_chapter01.widget.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import fastcampus.aop.aop_part6_chapter01.data.entity.LocationLatLngEntity
import fastcampus.aop.aop_part6_chapter01.screen.main.home.restaurant.RestaurantListFragment

class RestaurantListFragmentPagerAdapter(
    fragment: Fragment,
    val fragmentList: List<RestaurantListFragment>,
    var locationLatLngEntity: LocationLatLngEntity
): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}