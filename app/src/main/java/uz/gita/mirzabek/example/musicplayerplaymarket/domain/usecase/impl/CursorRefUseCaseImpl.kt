package uz.gita.mirzabek.example.musicplayerplaymarket.domain.usecase.impl


import kotlinx.coroutines.flow.flow
import uz.gita.mirzabek.example.musicplayerplaymarket.domain.repository.AppRepository
import uz.gita.mirzabek.example.musicplayerplaymarket.domain.usecase.CursorRefUseCase
import javax.inject.Inject

class CursorRefUseCaseImpl @Inject constructor(
    private val appRepository:AppRepository
):CursorRefUseCase{
    override fun invoke() = flow{
        emit(appRepository.getCursor())
    }

}