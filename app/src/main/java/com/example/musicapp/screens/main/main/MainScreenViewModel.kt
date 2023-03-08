package com.example.musicapp.screens.main.main

import androidx.lifecycle.ViewModel
import com.example.musicapp.data.DataOrException
import com.example.musicapp.model.Music
import com.example.musicapp.repository.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repository: MainRepo) : ViewModel() {
    suspend fun getMusicData(): DataOrException<Music, Boolean, Exception> {
        return repository.getMusic()
    }
}