package com.example.musicapp.screens.main.main

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.musicapp.data.DataOrException
import com.example.musicapp.model.Data
import com.example.musicapp.model.Music
import com.example.musicapp.navigation.AppRoutes

@Composable
fun MainScreen(navController: NavController,
               mainViewModel: MainScreenViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = Color(0xFF192358)
    ) {
        val musicData = produceState<DataOrException<Music, Boolean, Exception>>(
            initialValue = DataOrException(loading = true)) {
         value = mainViewModel.getMusicData()
        }.value

        if (musicData.loading == true) {
            CircularProgressIndicator(strokeWidth = 12.dp, color = Color.Magenta)
            Log.d("MainScreen", "MainScreen: $musicData")
        } else if(musicData.data != null){
            AlbumColumn(data = musicData.data!!.data, mainViewModel, navController)
        }
    }
}

@Composable
fun AlbumColumn(data: List<Data>, mainViewModel: MainScreenViewModel, navController: NavController){
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center, content = {
        items(data) {item ->
            AlbumCard(item, mainViewModel, navController)
    }
    })
}

@Composable
fun AlbumCard(item: Data, mainViewModel: MainScreenViewModel, navController: NavController){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable(onClick = {
            mainViewModel.TrackId = item.id
            Log.d("MainScreen", "AlbumCard: ${item.album.cover_xl}")
            navController.navigate(AppRoutes.PlayerScreen.name)
        }),

        horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            modifier = Modifier.clip(RoundedCornerShape(5)),
            model = item.album.cover_xl,
            contentDescription = "An Image of the album", alignment = Alignment.TopCenter)

        Text(text = item.album.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White)
        Text(text = item.artist.name,
            style = MaterialTheme.typography.labelLarge,
            color = Color.LightGray)
    }
}