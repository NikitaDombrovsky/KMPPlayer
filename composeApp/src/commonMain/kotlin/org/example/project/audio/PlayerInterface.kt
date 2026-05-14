package org.example.project.audio

import kotlinx.coroutines.flow.StateFlow

interface PlayerInterface {
    val state: StateFlow<PlayerState>
    fun play(url: String)
    fun pause()
    fun resume()
    fun seekTo(positionMs: Long)
}

//expect fun createPlayerInterface(): PlayerInterface