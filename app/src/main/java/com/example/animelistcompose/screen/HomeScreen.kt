package com.example.animelistcompose.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.animelistcompose.Screen
import com.example.animelistcompose.data.ViewModelFactory
import com.example.animelistcompose.data.Waifu
import com.example.animelistcompose.data.WaifuViewModel
import com.example.animelistcompose.ui.theme.AnimeListComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController?) {
    Scaffold(
        topBar = {
            TopAppBarHome(navController)
        }
    ) {
        LazyWaifuList(it, navController)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarHome(navController: NavController?) {
    TopAppBar(
        title = { Text(text = "My Waifu List") },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Cyan),
        actions = {
            ButtonAbout(navController)
        }
    )
}

@Composable
fun ButtonAbout(navController: NavController?) {
    IconButton(onClick = {
        navController?.navigate(route = Screen.About.route)
    }) {
        Icon(imageVector = Icons.Default.Person, contentDescription = "about_page")
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun LazyWaifuList(paddingValues: PaddingValues, navController: NavController?) {
    val viewModel: WaifuViewModel =
        viewModel(factory = ViewModelFactory(LocalContext.current))
    val waifus : List<Waifu> by viewModel.waifus.collectAsState()
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
    ) {
        stickyHeader {
            SearchBar(){
                viewModel.search(it)
            }
        }
        items(waifus, key = { it.id }) {
            WaifuCard(navController, it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaifuCard(navController: NavController?, waifu: Waifu) {
    Card(
        modifier = Modifier.padding(20.dp),
        onClick = {
            navController?.navigate(
                Screen.Detail.route.replace(
                    "{waifuName}",
                    waifu.waifuName
                )
            )
        },
    ) {
        Image(
            painter = painterResource(id = waifu.waifuPhoto),
            contentDescription = waifu.waifuName,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = waifu.waifuName,
            style = TextStyle(),
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = waifu.waifuAnimeTitle
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(onQueryChange: (String) -> Unit) {
    var query by remember { mutableStateOf("") }
    TextField(value = query, onValueChange = {
        query = it
        onQueryChange(it)
    }, modifier = Modifier.fillMaxWidth(), label = {
        Text(text = "Search Waifu By Name")
    }, trailingIcon = {
        Icon(imageVector = Icons.Default.Search, contentDescription = "search_icon")
    })
}

@Preview
@Composable
fun HomeScreenPreview() {
    AnimeListComposeTheme {
        HomeScreen(null)
    }
}

