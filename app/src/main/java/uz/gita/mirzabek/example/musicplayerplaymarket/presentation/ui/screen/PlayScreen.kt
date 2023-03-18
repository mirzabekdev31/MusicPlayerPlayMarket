package uz.gita.mirzabek.example.musicplayerplaymarket.presentation.ui.screen

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mirzabek.example.musicplayerplaymarket.R
import uz.gita.mirzabek.example.musicplayerplaymarket.data.model.CommandEnum
import uz.gita.mirzabek.example.musicplayerplaymarket.data.model.MusicData
import uz.gita.mirzabek.example.musicplayerplaymarket.databinding.ScreenPlayBinding
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.controller.MusicManager
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.ui.servise.MusicPlayService
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.viewmodel.impl.PlayViewModelImpl
import uz.gita.mirzabek.example.musicplayerplaymarket.utils.setChangeProgress
import uz.infinity.app.utils.myApply

@AndroidEntryPoint
class PlayScreen :Fragment(R.layout.screen_play) {
    private val binding by viewBinding(ScreenPlayBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonNext.setOnClickListener { startMyService(CommandEnum.NEXT) }
        binding.buttonPrev.setOnClickListener { startMyService(CommandEnum.PREV) }
        binding.buttonManage.setOnClickListener { startMyService(CommandEnum.MANAGE) }
        binding.seekBar.setChangeProgress {}

        MusicManager.playMusicLiveData.observe(viewLifecycleOwner, playMusicObserver)
        MusicManager.isPlayingLiveData.observe(viewLifecycleOwner, isPlayingObserver)
        MusicManager.currentTimeLiveData.observe(viewLifecycleOwner, currentTimeObserver)
    }

    private val playMusicObserver = Observer<MusicData> {
        binding.seekBar.progress = (MusicManager.currentTime * 100 / MusicManager.fullTime).toInt()
        binding.textMusicName.text = it.title
        binding.textArtistName.text = it.artist
        binding.currentTime.text = MusicManager.currentTime.toString()
        binding.totalTime.text = it.duration.toString()
    }

    private val isPlayingObserver = Observer<Boolean> {
        if (it) binding.buttonManage.setImageResource(R.drawable.ic_pause)
        else binding.buttonManage.setImageResource(R.drawable.ic_play)
    }

    private val currentTimeObserver = Observer<Long> {
        binding.seekBar.progress = (MusicManager.currentTime * 100 / MusicManager.fullTime).toInt()
        binding.currentTime.text = MusicManager.currentTime.toString()
    }

    private fun startMyService(action: CommandEnum) {
        val intent = Intent(requireContext(), MusicPlayService::class.java)
        intent.putExtra("COMMAND", action)
        if (Build.VERSION.SDK_INT >= 26) {
            requireActivity().startForegroundService(intent)
        } else requireActivity().startService(intent)
    }
}