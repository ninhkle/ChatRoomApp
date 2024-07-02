package com.ninhkle.chatroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.ninhkle.chatroom.Injection
import com.ninhkle.chatroom.data.Message
import com.ninhkle.chatroom.data.MessageRepository
import com.ninhkle.chatroom.data.Result
import com.ninhkle.chatroom.data.User
import com.ninhkle.chatroom.data.UserRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MessageViewModel : ViewModel() {
    private val _messageRepository : MessageRepository
    private val _userRepository : UserRepository
    init {
        _messageRepository = MessageRepository(Injection.instance())
        _userRepository = UserRepository(FirebaseAuth.getInstance(), Injection.instance())
        loadCurrentUser()
    }

    private val _messages = MutableLiveData<List<Message>>()
    private val _roomId = MutableLiveData<String>()
    private val _currentUser = MutableLiveData<User>()
    val messages : LiveData<List<Message>> get() = _messages
    val currentUser: LiveData<User> get() = _currentUser

    private fun loadCurrentUser(){
        viewModelScope.launch {
            when (val result = _userRepository.getCurrentUser()) {
                is Result.Success -> _currentUser.value = result.data!!
                is Result.Error -> {
                    // Handle Error
                }
            }
        }
    }
    fun loadMessages() {
        viewModelScope.launch {
            if (_roomId != null) {
                _messageRepository.getChatMessages(_roomId.value.toString()).collect {
                    _messages.value = it
                }
            }
        }
    }
    fun sendMessage(text: String) {
        if (_currentUser.value != null) {
            val message = Message(
                senderFirstName = _currentUser.value!!.firstName,
                senderId = _currentUser.value!!.email,
                text = text
            )
            viewModelScope.launch {
                when (_messageRepository.sendMessage(_roomId.value.toString(), message)) {
                    is Result.Success -> Unit
                    is Result.Error -> {

                    }
                }
            }
        }
    }
    fun setRoomId(roomId: String) {
        _roomId.value = roomId
        loadMessages()
    }
}










