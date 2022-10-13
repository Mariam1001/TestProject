package com.gsolutions.testproject.views.viewmodels

import android.app.Application
import android.content.ContentUris
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gsolutions.testproject.data.Photos
import com.gsolutions.testproject.utils.Constants
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext


class PhotosViewModel(application: Application) : AndroidViewModel(application),CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job


    private val _allImagesFromGallery = MutableLiveData<List<Photos>>()
    val allImagesFromGallery: LiveData<List<Photos>>
        get() = _allImagesFromGallery

    private fun getAllImages(): List<Photos> {
        val allImages = mutableListOf<Photos>()

        val imageProjection = arrayOf(
            MediaStore.Images.Media._ID
        )

        val imageSortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

        val cursor = getApplication<Application>().contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            imageProjection,
            null,
            null,
            imageSortOrder
        )

        cursor.use {
            if (cursor != null) {
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                while (cursor.moveToNext()) {
                    if (cursor.position > 3 && cursor.position % 2 == 0){
                        val photo = Photos(
                            id = allImages.size,
                            uri = null,
                            type = if (cursor.position % 4 == 0) Constants.TYPE_PHOTO_2 else Constants.TYPE_PHOTO_3
                        )
                        allImages.add(photo)
                    }
                    val photo = Photos(
                        id = allImages.size,
                        uri = ContentUris.withAppendedId(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                cursor.getLong(idColumn)
                            ),
                        type = Constants.TYPE_PHOTO_1
                    )
                    allImages.add(photo)
                }
            } else {
                Log.d("AddViewModel", "Cursor is null!")
            }
        }
        return allImages
    }

    fun loadAllImages() {
        launch {
            val result = withContext(Dispatchers.IO) { getAllImages()}
            _allImagesFromGallery.value = result
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}