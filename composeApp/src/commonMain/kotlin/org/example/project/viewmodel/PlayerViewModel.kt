package org.example.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.audio.PlayerInterface
import org.example.project.audio.createPlayerInterface
import org.example.project.data.MusicRepository
import org.example.project.data.Song
import org.example.project.network.createHttpClient

class PlayerViewModel : ViewModel() {
    private val client = createHttpClient()
    private val repository = MusicRepository(client)

    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val song: StateFlow<List<Song>> = _songs.asStateFlow()

    private val audioPlayer: PlayerInterface = createPlayerInterface()

    init {
        loadSongs()
        audioPlayer.play("https://myheihcbyastpymcpqiy.supabase.co/storage/v1/object/public/audio/music1.mp3")
    }

    private fun loadSongs(){
        viewModelScope.launch {
            _songs.value = repository.getSongs()
        }
    }

}