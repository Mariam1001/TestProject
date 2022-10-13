package com.gsolutions.testproject.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.evenlab.dijisafe.base.BaseFragment
import com.gsolutions.testproject.R
import com.gsolutions.testproject.data.Photos
import com.gsolutions.testproject.databinding.FragmentPhotoDetailBinding
import com.gsolutions.testproject.databinding.FragmentPhotosBinding
import com.gsolutions.testproject.utils.Constants
import com.gsolutions.testproject.views.adapters.PhotosAdapter
import com.gsolutions.testproject.views.viewmodels.PhotosViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PhotosFragment : BaseFragment() {

    private var _binding: FragmentPhotosBinding? = null;
    private val binding get() = _binding!!;
    private val viewModel: PhotosViewModel by viewModel()
    private val adapter = PhotosAdapter(::photoClickListener)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photos, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeGetImages()
    }

    private fun initView(){
        binding.photosRecyclerView.adapter = adapter
        val glm = GridLayoutManager(context, 2)
        glm.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (adapter.currentList.get(position).type == Constants.TYPE_PHOTO_2
                    || adapter.currentList.get(position).type == Constants.TYPE_PHOTO_3)
                    return 2
                else
                    return 1
            }
        }
        binding.photosRecyclerView.setLayoutManager(glm)
        viewModel.loadAllImages()
    }

    private fun observeGetImages() {
        viewModel.allImagesFromGallery.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun photoClickListener(photos: Photos) {
        val diraction = PhotoDetailFragmentDirections.actionPhotosToDetail(photos.uri.toString())
        findNavController().navigate(diraction)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}