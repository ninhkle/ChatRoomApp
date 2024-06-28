package com.ninhkle.chatroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninhkle.chatroom.Injection
import com.ninhkle.chatroom.data.Result
import com.ninhkle.chatroom.data.Room
import com.ninhkle.chatroom.data.RoomRepository
import kotlinx.coroutines.launch

class RoomViewModel : ViewModel() {
    private val _rooms = MutableLiveData<List<Room>>()
    val rooms: LiveData<List<Room>> get() = _rooms
    private val _roomRepository: RoomRepository = RoomRepository(Injection.instance())

    init {
        loadRooms()
    }
    fun createRoom(name: String) {
        viewModelScope.launch {
            _roomRepository.createRoom(name)
        }
    }
    fun loadRooms() {
        viewModelScope.launch {
            when (val result = _roomRepository.getRoom()) {
                is Result.Success -> _rooms.value = result.data
                is Result.Error -> {

                }
            }
        }
    }

}