package com.example.musicapp.screens.main.main

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.musicapp.data.DataOrException
import com.example.musicapp.model.Data
import com.example.musicapp.model.Music
import com.example.musicapp.model.Tracks
import com.example.musicapp.repository.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repository: MainRepo) : ViewModel() {
    var TrackId: Long = 0
    suspend fun getMusicData(): DataOrException<Music, Boolean, Exception> {
        return repository.getMusic()
    }
    suspend fun getMusicById(): DataOrException<Tracks, Boolean, Exception> {
        Log.d("MainViewModel", "$TrackId")
        return repository.getMusicById(TrackId)
    }
}