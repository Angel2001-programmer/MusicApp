package com.example.musicapp.screens.main.player

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.musicapp.model.Tracks
import com.example.musicapp.screens.main.main.MainScreenViewModel

@Composable
fun PlayerScreen(
    navController: NavController,
    mainViewModel: MainScreenViewModel = hiltViewModel(),
){
    val favouriteTaps = remember {
        mutableStateOf(1)
    }

    val playTaps = remember{
        mutableStateOf(1)
    }

    val favouriteState = remember{
        mutableStateOf(false
        )
    }
    val playState = remember{
        mutableStateOf(false
        )
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = Color(0xFF192358)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            val musicId = produceState<DataOrException<Tracks, Boolean, Exception>>(
                initialValue = DataOrException(loading = true)
            ) {
                value = mainViewModel.getMusicById()
            }.value

            if (musicId.loading == true) {
                CircularProgressIndicator(strokeWidth = 12.dp, color = Color.Magenta)
                Log.d("PlayerScreen", "${musicId.data}")
            } else if(musicId.data != null){
                PreviewAlbum(item = musicId.data!!)
            }

            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                progress = 1f,
                color = Color(0xFF009688)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {


                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        modifier = Modifier.size(150.dp),
                        imageVector = Icons.Default.SkipPrevious,
                        contentDescription = "return to last song Button"
                    )
                }
                IconButton(onClick = { ButtonState(playTaps, playState)
                    Log.d("PlayerScreen", "${mainViewModel.TrackId}")}) {
                    if (playState.value) {
                        Icon(
                            modifier = Modifier.size(150.dp),
                            imageVector = Icons.Default.Pause,
                            contentDescription = "Pause Button"
                        )
                    } else {
                        Icon(
                            modifier = Modifier.size(150.dp),
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Play Button"
                        )
                        playState.value = false

                    }
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        modifier = Modifier.size(150.dp),
                        imageVector = Icons.Default.SkipNext,
                        contentDescription = "Skip Button"
                    )
                }
                IconButton(onClick = {
                    ButtonState(favouriteTaps, favouriteState)
                }) {
                    if (favouriteState.value) {
                        Icon(
                            modifier = Modifier.size(150.dp),
                            imageVector = Icons.Outlined.Favorite,
                            contentDescription = "Play Button"
                        )
                    } else {
                        Icon(
                            modifier = Modifier.size(150.dp),
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = "Play Button"
                        )
                        favouriteState.value = false
                    }
                }
            }
        }
    }
}

@Composable
fun PreviewAlbum(item: Tracks) {
    AsyncImage(
        modifier = Modifier.clip(RoundedCornerShape(5)),
        model = item.album.cover_xl,
        contentDescription = "An Image of the album", alignment = Alignment.TopCenter)

    Text(modifier = Modifier.padding(8.dp),
        text = item.title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = Color.White)
    Text(text = item.artist.name,
        style = MaterialTheme.typography.labelLarge,
        color = Color.LightGray)
}

fun ButtonState(buttonTaps: MutableState<Int>, buttonState: MutableState<Boolean>){
    if (buttonTaps.value <= 1){
        buttonState.value = true
        buttonTaps.value++
    } else {
        buttonState.value = false
        buttonTaps.value--
    }
}