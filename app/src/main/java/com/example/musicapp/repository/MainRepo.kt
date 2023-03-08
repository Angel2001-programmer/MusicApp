package com.example.musicapp.repository

import android.util.Log
import com.example.musicapp.data.DataOrException
import com.example.musicapp.model.Music
import com.example.musicapp.network.MusicAPI
import javax.inject.Inject

class MainRepo @Inject constructor(private val api: MusicAPI) {
    suspend fun getMusic(): DataOrException<Music, Boolean, Exception> {
        val response = try {
            api.getMusic()
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        Log.d("INSIDE", "getMusic: $response")
        return DataOrException(data = response)
    }
}