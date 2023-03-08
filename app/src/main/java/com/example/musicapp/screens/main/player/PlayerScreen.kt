package com.example.musicapp.screens.main.player

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.musicapp.model.Data
import com.example.musicapp.screens.main.main.AlbumCard

@Composable
fun PlayerScreen(item: Data){
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
        AlbumCard(item)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(modifier = Modifier.size(150.dp), imageVector = Icons.Default.SkipPrevious, contentDescription = "return to last song Button")
            }
            IconButton(onClick = { ButtonState(playTaps, playState) }) {
                if(playState.value){
                    Icon(modifier = Modifier.size(150.dp),
                        imageVector = Icons.Default.Pause,
                        contentDescription = "Pause Button")
                } else {
                    Icon(modifier = Modifier.size(150.dp),
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play Button")
                    playState.value = false

                }
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(modifier = Modifier.size(150.dp), imageVector = Icons.Default.SkipNext, contentDescription = "Skip Button")
            }
            IconButton(onClick = {
                ButtonState(favouriteTaps, favouriteState)
            }) {
                if(favouriteState.value){
                    Icon(modifier = Modifier.size(150.dp),
                        imageVector = Icons.Outlined.Favorite,
                        contentDescription = "Play Button")
                } else {
                    Icon(modifier = Modifier.size(150.dp),
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Play Button")
                    favouriteState.value = false

                }
            }
        }
    }
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