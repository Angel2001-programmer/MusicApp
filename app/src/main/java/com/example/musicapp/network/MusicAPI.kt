package com.example.musicapp.network

import com.example.musicapp.model.Music
import com.example.musicapp.utils.Query
import retrofit2.http.GET

interface MusicAPI {
    @GET(Query)
    suspend fun getMusic(): Music
}