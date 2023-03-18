package uz.gita.mirzabek.example.musicplayerplaymarket.presentation.viewmodel

import android.database.Cursor
import android.text.BoringLayout
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.StateFlow
import uz.gita.mirzabek.example.musicplayerplaymarket.data.model.MusicData

interface PlayViewModel {
   // val cursorRefFlow: StateFlow<Cursor?>
//    val playMusicFlow:StateFlow<MusicData>
//    val isPlayingFlow:StateFlow<Boolean>
//    val currentTimeFlow:StateFlow<Long>
    val currentTimeLiveData:StateFlow<Long>
    val playMusicLiveData:StateFlow<MusicData>
    val isPlayingLiveData:StateFlow<Boolean>


    /*
    val currentTimeLiveData = MutableLiveData<Long>()

    val playMusicLiveData = MutableLiveData<MusicData>()
    val isPlayingLiveData = MutableLiveData<Boolean>()
     */
}