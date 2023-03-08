package com.example.musicapp.screens.main

import android.util.Log
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.musicapp.data.DataOrException
import com.example.musicapp.model.Music

@Composable
fun MainScreen() {
    val mainViewModel: MainScreenViewModel = viewModel()

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        val MusicData = produceState<DataOrException<Music, Boolean, Exception>>(initialValue = DataOrException(loading = true)) {
         value = mainViewModel.getMusicData()
        }.value
        Log.d("MainScreen", "MainScreen: $MusicData")
    }
}