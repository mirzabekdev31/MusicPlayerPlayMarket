package uz.gita.mirzabek.example.musicplayerplaymarket.presentation.ui.screen

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mirzabek.example.musicplayerplaymarket.R
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.viewmodel.SplashViewModel
import uz.gita.mirzabek.example.musicplayerplaymarket.presentation.viewmodel.impl.SplashViewModelImpl
import uz.gita.mirzabek.example.musicplayerplaymarket.utils.checkPermissions


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen :Fragment(R.layout.screen_splash){

    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().checkPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            viewModel.getMusicFromLocal()
        }
    }
}

/*
private val binding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().checkPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            viewModel.getMusicFromLocal()
        }
    }
 */