package org.example.project.audio

import android.media.MediaPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


actual fun createPlayerInterface(): PlayerInterface = PlayerController()


class PlayerController : PlayerInterface {

    private var mediaPlayer: MediaPlayer? = null

    private val _state = MutableStateFlow(PlayerState())

    override val state: StateFlow<PlayerState> = _state.asStateFlow()

    override fun play(url: String) {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(url)

            setOnPreparedListener {
                it.start()
            }
            prepareAsync()
        }
    }

    override fun pause() {
        mediaPlayer?.pause()
    }

    override fun resume() {
        mediaPlayer?.start();
    }

    override fun seekTo(positionMs: Long) {
        mediaPlayer?.seekTo(positionMs.toInt())
    }
}