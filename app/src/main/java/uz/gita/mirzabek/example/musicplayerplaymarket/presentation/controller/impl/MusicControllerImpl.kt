package uz.gita.mirzabek.example.musicplayerplaymarket.presentation.controller.impl

import android.content.Context
import android.database.Cursor
import android.media.MediaPlayer
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mirzabek.example.musicplayerplaymarket.data.mapper.getMusicDataByPosition
import uz.gita.mirzabek.example.musicplayerplaymarket.data.model.CommandEnum
import uz.gita.mirzabek.example.musicplayerplaymarket.domain.usecase.CursorRefUseCase
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.controller.MusicController
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.controller.MusicManager
import javax.inject.Inject

class MusicControllerImpl @Inject constructor(
    private val cursorRefUseCase:CursorRefUseCase,
) :MusicController{
    private var _cursor:Cursor?=null
    private val cursor get() = _cursor!!
    private val scope= CoroutineScope(Dispatchers.IO+ Job())
    private var _mediaPlayer:MediaPlayer? = null
    private val mediaPlayer get() = _mediaPlayer!!

    override fun doneCommand(commandEnum: CommandEnum, context: Context) {
        when(commandEnum){
            CommandEnum.MANAGE ->{
                if (_mediaPlayer==null){
                    doneCommand(CommandEnum.PLAY,context)
                    return
                }
                if (mediaPlayer.isPlaying){
                    doneCommand(CommandEnum.PAUSE,context)
                }else doneCommand(CommandEnum.PLAY,context)
            }
            CommandEnum.PLAY->{
                _mediaPlayer=MediaPlayer.create(context,Uri.parse(cursor?.getMusicDataByPosition(MusicManager.selectMusicPos)?.data))
                mediaPlayer.setOnCompletionListener {
                    MusicManager.selectMusicPos++
                    doneCommand(CommandEnum.PLAY,context)
                }
                mediaPlayer.start()
            }
            CommandEnum.PAUSE->{

            }
            CommandEnum.PREV->{

            }
            CommandEnum.NEXT->{

            }
            CommandEnum.CANCEL->{

            }
        }
    }

    init {
        cursorRefUseCase.invoke().onEach { _cursor=it }.launchIn(scope)
    }

    override fun onCleared() {
        scope.cancel()
        _cursor=null
        _mediaPlayer?.release()
        _mediaPlayer=null
    }


}