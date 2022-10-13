package com.gsolutions.testproject.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.evenlab.dijisafe.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.gsolutions.testproject.R
import com.gsolutions.testproject.databinding.FragmentHomeBinding
import com.gsolutions.testproject.databinding.FragmentStartBinding
import com.gsolutions.testproject.views.adapters.HomePagerAdapter

class HomeFragment : BaseFragment() {

    private var _binding:FragmentHomeBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setupViewPager()
        setupTabLayout()
    }

    private fun initView(){
        binding.backBtn.setOnClickListener{
            activity?.onBackPressed()
        }
    }

    private fun setupTabLayout() {
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.photos)
                1 -> tab.text = getString(R.string.videos)
            }
        }.attach()
    }

    private fun setupViewPager() {
        val adapter = activity?.let { HomePagerAdapter(it, 2) }
        binding.viewPager.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}