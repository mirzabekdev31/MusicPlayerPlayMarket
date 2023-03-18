package uz.gita.mirzabek.example.musicplayerplaymarket.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.mirzabek.example.musicplayerplaymarket.domain.repository.AppRepository
import uz.gita.mirzabek.example.musicplayerplaymarket.domain.usecase.GetMusicFromLocalUseCase
import javax.inject.Inject

class GetMusicFromLocalUseCaseImpl
@Inject constructor(
    private val appRepository: AppRepository
):GetMusicFromLocalUseCase{
    override fun invoke(): Flow<Unit> = flow {
        emit(appRepository.getMusicsFromLocal())
    }
}