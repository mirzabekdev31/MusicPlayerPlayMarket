package uz.gita.mirzabek.example.musicplayerplaymarket.domain.repository.impl
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gita.mirzabek.example.musicplayerplaymarket.domain.repository.AppRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    @ApplicationContext private val context:Context
) :AppRepository{
    private lateinit var myCursor: Cursor

    private val list= arrayOf(
        MediaStore.Audio.Media._ID,
        MediaStore.Audio.Media.ARTIST,
        MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.DATA,
        MediaStore.Audio.Media.DURATION
    )


    override suspend fun getMusicsFromLocal() {
        val cursor= context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            list,
            MediaStore.Audio.Media.IS_MUSIC +"!=0",
            null,
            null
        )
        cursor?.let { myCursor=it }
    }

    override fun getCursor() :Cursor=myCursor
}