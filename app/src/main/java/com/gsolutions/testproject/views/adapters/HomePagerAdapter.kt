package com.gsolutions.testproject.views.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gsolutions.testproject.views.fragments.PhotosFragment
import com.gsolutions.testproject.views.fragments.VideosFragment

class HomePagerAdapter (fragmentActivity: FragmentActivity, private var totalCount: Int) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return totalCount
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PhotosFragment()
            1 -> VideosFragment()
            else -> PhotosFragment()
        }
    }
}
