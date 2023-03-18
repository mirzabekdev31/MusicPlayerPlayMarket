package uz.gita.mirzabek.example.musicplayerplaymarket.di

import androidx.transition.Visibility.Mode
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mirzabek.example.musicplayerplaymarket.navigation.AppNavigationManager
import uz.gita.mirzabek.example.musicplayerplaymarket.navigation.AppNavigator
import uz.gita.mirzabek.example.musicplayerplaymarket.navigation.NavigationHandler


@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun bindsAppNavigator(impl: AppNavigationManager): AppNavigator

    @Binds
    fun bindsNavigationHandler(impl: AppNavigationManager): NavigationHandler
}