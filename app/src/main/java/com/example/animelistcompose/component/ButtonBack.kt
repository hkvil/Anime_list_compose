package com.example.animelistcompose.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun ButtonBack(navController: NavHostController?) {
    IconButton(onClick = {
        navController?.popBackStack()
    }) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back_button")
    }
}