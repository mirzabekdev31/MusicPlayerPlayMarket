package uz.gita.mirzabek.example.musicplayerplaymarket.navigation

import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow

interface AppNavigator {
    val navigationFlow: Flow<NavController.() -> Unit>
}