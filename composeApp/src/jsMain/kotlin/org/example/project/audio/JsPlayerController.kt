package org.example.project.audio

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

actual fun createPlayerInterface(): PlayerInterface = JsPlayerController()


class JsPlayerController() : PlayerInterface {

    private val audio: dynamic = js("new Audio()")
    private val _state = MutableStateFlow(PlayerState())

    override val state: StateFlow<PlayerState> = _state.asStateFlow()

    override fun play(url: String) {

        audio.src = url
        audio.load()
        audio.play()

        _state.update { it.copy(isLoading = false, isPlaying = true)}

    }

    override fun pause() {
        audio.pause()
        _state.update { it.copy(isPlaying = false)}
        TODO("Not yet implemented")
    }

    override fun resume() {
        audio.play();
        _state.update { it.copy(isPlaying = true)}
    }

    override fun seekTo(positionMs: Long) {
        TODO("Not yet implemented")
    }

}