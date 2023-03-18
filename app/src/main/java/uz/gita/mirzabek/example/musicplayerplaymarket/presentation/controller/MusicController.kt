package uz.gita.mirzabek.example.musicplayerplaymarket.presentation.controller

import android.content.Context
import uz.gita.mirzabek.example.musicplayerplaymarket.data.model.CommandEnum

interface MusicController {
    fun doneCommand(commandEnum: CommandEnum, context: Context)
    fun onCleared()
}