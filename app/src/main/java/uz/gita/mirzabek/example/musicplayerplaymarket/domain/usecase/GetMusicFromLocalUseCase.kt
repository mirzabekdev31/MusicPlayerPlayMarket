package uz.gita.mirzabek.example.musicplayerplaymarket.domain.usecase

import android.database.Cursor
import kotlinx.coroutines.flow.Flow

interface GetMusicFromLocalUseCase {
    fun invoke(): Flow<Unit>
}