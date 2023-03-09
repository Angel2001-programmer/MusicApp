package com.example.musicapp.network

import com.example.musicapp.model.Music
import com.example.musicapp.model.Tracks
import com.example.musicapp.utils.AllTracks
import com.example.musicapp.utils.JsonFormat
import com.example.musicapp.utils.Track
import retrofit2.http.GET
import retrofit2.http.Path

interface MusicAPI {
    @GET(AllTracks + JsonFormat)
    suspend fun getMusic(): Music
    @GET("$Track/{id}$JsonFormat")
    suspend fun getMusicById(@Path("id") Id: Long): Tracks
}