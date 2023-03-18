package uz.gita.mirzabek.example.musicplayerplaymarket.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mirzabek.example.musicplayerplaymarket.domain.repository.AppRepository
import uz.gita.mirzabek.example.musicplayerplaymarket.domain.repository.impl.AppRepositoryImpl


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsAppRepository(impl: AppRepositoryImpl): AppRepository
}