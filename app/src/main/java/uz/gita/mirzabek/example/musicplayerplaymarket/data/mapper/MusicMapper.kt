package uz.gita.mirzabek.example.musicplayerplaymarket.data.mapper

import android.database.Cursor
import uz.gita.mirzabek.example.musicplayerplaymarket.data.model.MusicData


fun Cursor.getMusicDataByPosition(pos:Int):MusicData{
    this.moveToPosition(pos)
    return MusicData(
        this.getInt(0),
        this.getString(1),
        this.getString(2),
        this.getString(3),
        this.getLong(4)
    )
}