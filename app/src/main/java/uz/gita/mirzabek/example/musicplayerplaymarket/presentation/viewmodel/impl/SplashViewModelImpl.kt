package uz.gita.mirzabek.example.musicplayerplaymarket.presentation.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mirzabek.example.musicplayerplaymarket.domain.usecase.GetMusicFromLocalUseCase
import uz.gita.mirzabek.example.musicplayerplaymarket.navigation.NavigationHandler
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.ui.screen.SplashScreen
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.ui.screen.SplashScreenDirections
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.viewmodel.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val navigationHandler: NavigationHandler,
    private val getMusicFromLocalUseCase: GetMusicFromLocalUseCase
) :SplashViewModel,ViewModel(){

    override fun getMusicFromLocal() {
        getMusicFromLocalUseCase.invoke().onEach {
            delay(1000)
            navigationHandler.navigationTo(SplashScreenDirections.actionSplashScreenToPlayListScreen())
        }.launchIn(viewModelScope)
    }
}