package org.example.project

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.project.audio.PlayerInterface
import org.example.project.audio.PlayerState

actual fun createPlayerInterface(): PlayerInterface = JsPlayerController()


class JsPlayerController() : PlayerInterface {

    private val audio: dynamic = js("new Audio()")
    private val _state = MutableStateFlow(PlayerState())

    override val state: StateFlow<PlayerState> = _state.asStateFlow()

    override fun play(url: String) {

        audio.src = url
        audio.load()

        _state.update { it.copy(isLoading = false, isPlaying = true)}

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