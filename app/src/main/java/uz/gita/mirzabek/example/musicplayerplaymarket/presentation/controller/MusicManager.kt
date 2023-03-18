package uz.gita.mirzabek.example.musicplayerplaymarket.presentation.controller

import android.database.Cursor
import androidx.lifecycle.MutableLiveData
import uz.gita.mirzabek.example.musicplayerplaymarket.data.model.CommandEnum
import uz.gita.mirzabek.example.musicplayerplaymarket.data.model.MusicData

object MusicManager {
    var cursor: Cursor? = null
    var selectMusicPos: Int = -1
    var lastCommand : CommandEnum = CommandEnum.PLAY

    var currentTime : Long = 0L
    var fullTime : Long = 0L

    val currentTimeLiveData = MutableLiveData<Long>()

    val playMusicLiveData = MutableLiveData<MusicData>()
    val isPlayingLiveData = MutableLiveData<Boolean>()
}