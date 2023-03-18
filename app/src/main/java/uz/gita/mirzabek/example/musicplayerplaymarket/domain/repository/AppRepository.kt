package uz.gita.mirzabek.example.musicplayerplaymarket.domain.repository

import android.database.Cursor

interface AppRepository {
    suspend fun getMusicsFromLocal()
    fun getCursor():Cursor
}