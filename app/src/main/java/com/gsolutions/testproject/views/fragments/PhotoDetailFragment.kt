package com.gsolutions.testproject.views.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.evenlab.dijisafe.base.BaseFragment
import com.gsolutions.testproject.R
import com.gsolutions.testproject.databinding.FragmentHomeBinding
import com.gsolutions.testproject.databinding.FragmentPhotoDetailBinding

class PhotoDetailFragment  : BaseFragment() {

    private var _binding: FragmentPhotoDetailBinding? = null;
    private val binding get() = _binding!!;
    private val args: PhotoDetailFragmentArgs by navArgs()
    private var photoId = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo_detail, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoId = args.photoId
        initView()
    }

    private fun initView(){
        binding.backBtn.setOnClickListener{
            activity?.onBackPressed()
        }
        context?.let {
            Glide.with(it)
                .load(Uri.parse(photoId)) // Uri of the picture
                .into(binding.detailImg)
        };
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}