package uz.gita.mirzabek.example.musicplayerplaymarket.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.controller.MusicController
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.controller.impl.MusicControllerImpl

@Module
@InstallIn(SingletonComponent::class)
interface ControllerModule {
    @Binds
    fun bindMusicController(impl: MusicControllerImpl): MusicController
}