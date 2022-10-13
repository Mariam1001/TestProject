package com.gsolutions.testproject.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.evenlab.dijisafe.base.BaseFragment
import com.gsolutions.testproject.R
import com.gsolutions.testproject.databinding.FragmentHomeBinding
import com.gsolutions.testproject.databinding.FragmentVideosBinding

class VideosFragment : BaseFragment() {

    private lateinit var binding: FragmentVideosBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_videos, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){

    }

}