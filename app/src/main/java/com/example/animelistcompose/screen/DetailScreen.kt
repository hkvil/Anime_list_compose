package com.example.animelistcompose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.animelistcompose.component.ButtonBack
import com.example.animelistcompose.data.Waifu
import com.example.animelistcompose.data.getWaifuByName
import com.example.animelistcompose.ui.theme.AnimeListComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController?, waifu: Waifu?) {
    Scaffold(
        topBar = {
            TopAppBarDetail(navController, waifu)
        }
    ) {
        ColumnDetail(it, waifu)
    }
}

@Composable
fun ColumnDetail(paddingValues: PaddingValues, waifu: Waifu?) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = waifu!!.waifuPhoto),
            contentDescription = "waifu_photo",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )
        Text(text = "PERSONAL INFORMATION")
        LineDivider()
        FieldRow("Name", waifu.waifuName)
        FieldRow("Age", waifu.waifuAge)
        FieldRow("Anime", waifu.waifuAnimeTitle)
        FieldRow("Birthday", waifu.waifuBirthday)
        FieldRow("Voice Actor Name", waifu.waifuVoiceActor)
        FieldRow("Voice Actor Photo", waifu.waifuVoiceActorPhoto)
        Text(text = "INTRODUCTION")
        LineDivider()
        Text(text = waifu.waifuIntro)
    }
}

@Composable
fun LineDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Black)
    )
}

@Composable
fun FieldRow(fieldName: String, data: Any) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
    ) {
        Text(text = fieldName, modifier = Modifier.weight(2f))
        Text(text = ":", modifier = Modifier.weight(1f))
        if (data is String) {
            Text(text = data.toString(), modifier = Modifier.weight(2f))
        } else if (data is Int) {
            Image(
                painter = painterResource(id = data),
                contentDescription = "voice_actor",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .weight(2f),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarDetail(navController: NavHostController?, waifu: Waifu?) {
    TopAppBar(
        title = { Text(text = "${waifu?.waifuName}") },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Cyan),
        navigationIcon = {
            ButtonBack(navController)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val previewWaifu = getWaifuByName(name = "Rem")
    AnimeListComposeTheme {
        DetailScreen(null, previewWaifu)
    }
}
