package uz.gita.mirzabek.example.musicplayerplaymarket.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mirzabek.example.musicplayerplaymarket.domain.usecase.CursorRefUseCase
import uz.gita.mirzabek.example.musicplayerplaymarket.domain.usecase.GetMusicFromLocalUseCase
import uz.gita.mirzabek.example.musicplayerplaymarket.domain.usecase.impl.CursorRefUseCaseImpl
import uz.gita.mirzabek.example.musicplayerplaymarket.domain.usecase.impl.GetMusicFromLocalUseCaseImpl


@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindsCursorRefUseCase(impl: CursorRefUseCaseImpl): CursorRefUseCase

    @Binds
    fun bindsGetMusicFromLocalUseCase(impl: GetMusicFromLocalUseCaseImpl): GetMusicFromLocalUseCase
}