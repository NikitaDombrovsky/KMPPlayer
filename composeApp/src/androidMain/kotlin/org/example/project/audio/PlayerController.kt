package org.example.project.audio

import android.media.MediaPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.project.PlayerScreen


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
            _state.update { it.copy(isLoading = false, isPlaying = true)}
            prepareAsync()
        }
    }

    override fun pause() {
        mediaPlayer?.pause()
        _state.update { it.copy(isPlaying = false)}
    }

    override fun resume() {
        mediaPlayer?.start();
        _state.update { it.copy(isPlaying = true)}
    }

    override fun seekTo(positionMs: Long) {
        mediaPlayer?.seekTo(positionMs.toInt())
    }
}