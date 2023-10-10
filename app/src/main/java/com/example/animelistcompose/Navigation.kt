package com.example.animelistcompose

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animelistcompose.data.ViewModelFactory
import com.example.animelistcompose.data.WaifuViewModel
import com.example.animelistcompose.data.getWaifuByName
import com.example.animelistcompose.screen.AboutScreen
import com.example.animelistcompose.screen.DetailScreen
import com.example.animelistcompose.screen.HomeScreen


sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen("home", R.string.home)
    object About : Screen("about", R.string.about)
    object Detail : Screen("detail/{waifuName}", R.string.detail)
}

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)

@Composable
fun AnimelistNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.About.route) {
            AboutScreen(navController)
        }
        composable(
            Screen.Detail.route) {
            val waifuName = it.arguments?.getString("waifuName")
            val waifu = getWaifuByName(name = waifuName.toString())
            DetailScreen(navController,waifu)
        }
    }
}

