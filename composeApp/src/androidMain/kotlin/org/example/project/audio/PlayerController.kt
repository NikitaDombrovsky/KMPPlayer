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
        TODO("Not yet implemented")
    }

    override fun resume() {
        TODO("Not yet implemented")
    }

    override fun seekTo(positionMs: Long) {
        TODO("Not yet implemented")
    }
}