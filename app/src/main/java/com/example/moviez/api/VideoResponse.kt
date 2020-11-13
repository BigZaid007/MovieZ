package com.example.moviez.api

import android.provider.MediaStore
import com.google.gson.annotations.SerializedName

class VideoResponse {

    @SerializedName("results")
    val videos:List<MediaStore.Video>?=null
}