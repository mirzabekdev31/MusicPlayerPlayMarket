package uz.gita.mirzabek.example.musicplayerplaymarket.presentation.ui.servise
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mirzabek.example.musicplayerplaymarket.R
import uz.gita.mirzabek.example.musicplayerplaymarket.data.model.CommandEnum
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.controller.MusicController
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.controller.MusicManager
import uz.infinity.app.utils.myLog
import javax.inject.Inject

@AndroidEntryPoint
class MusicPlayService :Service(){
    override fun onBind(p0: Intent?): IBinder? { return null }
    private val CHANNEL_ID="GITA"
    private var manager:NotificationManager?=null

    @Inject
    lateinit var controller:MusicController

    override fun onCreate() {
        super.onCreate()
        createChanel()
        createNotification()
    }

    private fun createChanel(){
        if (Build.VERSION.SDK_INT>=26){
            val chanel=NotificationChannel(CHANNEL_ID,"Example",NotificationManager.IMPORTANCE_DEFAULT)
            chanel.setSound(null,null)
            manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager?.createNotificationChannel(chanel)
        }
    }

    private fun createNotification(){
        val notification=NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("GITA")
            .setCustomContentView(createRemoteView())
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .build()
        startForeground(1,notification)
    }

    private fun createRemoteView():RemoteViews{
        val remoteView=RemoteViews(this.packageName,R.layout.remote_view)
        remoteView.setOnClickPendingIntent(R.id.buttonPrev, createPendingIntent(CommandEnum.PREV))
        remoteView.setOnClickPendingIntent(R.id.buttonCancel, createPendingIntent(CommandEnum.CANCEL))
        remoteView.setOnClickPendingIntent(R.id.buttonNext, createPendingIntent(CommandEnum.NEXT))
        remoteView.setOnClickPendingIntent(R.id.buttonManage, createPendingIntent(CommandEnum.MANAGE))
        return remoteView
    }

    private fun createPendingIntent(commandEnum: CommandEnum): PendingIntent {
        val intent = Intent(this, MusicPlayService::class.java)
        myLog("commandEnum = ${commandEnum}")
        MusicManager.lastCommand = commandEnum
        return PendingIntent.getService(this, commandEnum.pos, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        controller.doneCommand(MusicManager.lastCommand, this)
        myLog("command = ${MusicManager.lastCommand}")
        return START_NOT_STICKY
    }

}