package uz.gita.mirzabek.example.musicplayerplaymarket.presentation.viewmodel.impl

import android.database.Cursor
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mirzabek.example.musicplayerplaymarket.domain.usecase.CursorRefUseCase
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.viewmodel.PlayListViewModel
import javax.inject.Inject

@HiltViewModel
class PlayListViewModelImpl @Inject constructor(
    private val cursorRefUseCase: CursorRefUseCase
) :PlayListViewModel,ViewModel(){
    override val cursorRefFlow= MutableStateFlow<Cursor?>(null)
    override val isPlayingFlow= MutableStateFlow<Boolean?>(false)

    init {
        cursorRefUseCase.invoke().onEach {
            cursorRefFlow.emit(it)
        }.launchIn(viewModelScope)
    }
}