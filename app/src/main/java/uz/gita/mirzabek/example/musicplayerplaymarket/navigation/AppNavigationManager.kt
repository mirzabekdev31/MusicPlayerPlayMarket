package uz.gita.mirzabek.example.musicplayerplaymarket.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppNavigationManager @Inject constructor() : AppNavigator, NavigationHandler{
    override val navigationFlow= MutableSharedFlow<NavController.() -> Unit>()

    private suspend fun navigate(block: NavController.() -> Unit) {
        navigationFlow.emit(block)
    }

    override suspend fun back() { navigate { popBackStack() } }

    override suspend fun navigationTo(direction: NavDirections) { navigate { navigate(direction) } }
}