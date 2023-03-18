package uz.gita.mirzabek.example.musicplayerplaymarket.presentation.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.mirzabek.example.musicplayerplaymarket.data.model.MusicData
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.viewmodel.PlayViewModel
import javax.inject.Inject

@HiltViewModel
class PlayViewModelImpl @Inject constructor() :PlayViewModel,ViewModel(){
    //    override val playMusicFlow= MutableStateFlow<MusicData>(MusicData(0,"","","",0))
//    override val isPlayingFlow= MutableStateFlow<Boolean>(false)
//    override val currentTimeFlow= MutableStateFlow<Long>(0)
    override val currentTimeLiveData= MutableStateFlow<Long>(0)
    override val playMusicLiveData=MutableStateFlow<MusicData>(MusicData(0,"","","",0))
    override val isPlayingLiveData= MutableStateFlow<Boolean>(false)

}