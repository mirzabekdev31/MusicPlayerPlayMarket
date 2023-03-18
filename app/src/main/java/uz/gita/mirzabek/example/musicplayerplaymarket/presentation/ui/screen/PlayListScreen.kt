package uz.gita.mirzabek.example.musicplayerplaymarket.presentation.ui.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.os.Build

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mirzabek.example.musicplayerplaymarket.R
import uz.gita.mirzabek.example.musicplayerplaymarket.data.model.CommandEnum
import uz.gita.mirzabek.example.musicplayerplaymarket.databinding.ScreenPlayListBinding
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.controller.MusicManager
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.ui.adapter.MusicAdapter
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.ui.servise.MusicPlayService
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.viewmodel.PlayListViewModel
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.viewmodel.impl.PlayListViewModelImpl

@AndroidEntryPoint
class PlayListScreen :Fragment(R.layout.screen_play_list){
    private val binding by viewBinding(ScreenPlayListBinding::bind)
    private val viewModel: PlayListViewModel by viewModels<PlayListViewModelImpl>()
    private val adapter = MusicAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.musicList.layoutManager = LinearLayoutManager(requireContext())
        binding.musicList.adapter = adapter
        adapter.cursor = MusicManager.cursor

        binding.bottomPart.setOnClickListener {
            findNavController().navigate(R.id.action_playListScreen_to_playScreen)
        }
        binding.buttonNextScreen.setOnClickListener { startMyService(CommandEnum.NEXT) }
        binding.buttonPrevScreen.setOnClickListener { startMyService(CommandEnum.PREV) }
        binding.buttonManageScreen.setOnClickListener { startMyService(CommandEnum.MANAGE) }
        adapter.setSelectMusicPositionListener {
            MusicManager.selectMusicPos = it
            startMyService(CommandEnum.PLAY)
        }

        viewModel.cursorRefFlow.onEach { it?.let { showMusics(it) } }.launchIn(lifecycleScope)
    }

    private fun startMyService(action  :CommandEnum) {
        val intent = Intent(requireContext(), MusicPlayService::class.java)
        intent.putExtra("COMMAND", action)
        if (Build.VERSION.SDK_INT >= 26) {
            requireActivity().startForegroundService(intent)
        } else requireActivity().startService(intent)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showMusics(cursor: Cursor) {
        adapter.cursor = cursor
        adapter.notifyDataSetChanged()
    }

//    private val playMusicObserver = Observer<MusicData> { data ->
//        binding.apply {
//            textMusicNameScreen.text= data.title
//            textArtistNameScreen.text = data.artist
//        }
//    }
//
//    private val isPlayingObserver = Observer<Boolean> {
//        if (it) binding.buttonManageScreen.setImageResource(R.drawable.ic_pause)
//        else binding.buttonManageScreen.setImageResource(R.drawable.ic_play)
//    }
}