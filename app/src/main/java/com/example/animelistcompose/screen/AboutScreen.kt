package com.example.animelistcompose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.animelistcompose.R
import com.example.animelistcompose.component.ButtonBack
import com.example.animelistcompose.ui.theme.AnimeListComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavHostController?) {
    Scaffold(
        topBar = {
            TopAppBarAbout(navController)
        }
    ) {
      ColumnAbout(it)
    }
}

@Composable
fun ColumnAbout(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageAvatar()
        TextName()
        TextEmail()
    }
}

@Composable
fun TextEmail() {
    Text(
        text = "elbert.herry11@gmail.com",
        style = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 18.sp
        )
    )
}

@Composable
fun TextName() {
    Text(
        text = "Hidayat",
        modifier = Modifier.padding(vertical = 10.dp),
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )
    )
}

@Composable
fun ImageAvatar() {
    Image(
        painter = painterResource(id = R.drawable.hidayat),
        contentDescription ="avatar ",
        modifier = Modifier
            .size(300.dp)
            .clip(CircleShape)
            .border(width = 1.dp, color = Color.Black, shape = CircleShape),
        contentScale = ContentScale.Crop
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarAbout(navController:NavHostController?) {
    TopAppBar(
        title = { Text(text = "About Page") },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Cyan),
        navigationIcon = { ButtonBack(navController)}
    )
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AnimeListComposeTheme {
        AboutScreen(null)
    }
}