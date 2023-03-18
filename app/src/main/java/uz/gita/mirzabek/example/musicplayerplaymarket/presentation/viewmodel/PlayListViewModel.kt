package uz.gita.mirzabek.example.musicplayerplaymarket.presentation.viewmodel

import android.database.Cursor
import kotlinx.coroutines.flow.StateFlow

interface PlayListViewModel {
    val cursorRefFlow: StateFlow<Cursor?>
    val isPlayingFlow:StateFlow<Boolean?>
}