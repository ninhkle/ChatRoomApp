package com.ninhkle.chatroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.ninhkle.chatroom.Injection
import com.ninhkle.chatroom.data.UserRepository
import kotlinx.coroutines.launch
import com.ninhkle.chatroom.data.Result

class AuthViewModel : ViewModel() {
    private val _userRepository : UserRepository
    init {
        _userRepository = UserRepository(
            auth = FirebaseAuth.getInstance(),
            firestore = Injection.instance()
        )
    }
    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult : LiveData<Result<Boolean>> get() = _authResult

    fun signUp(email: String, password: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            _authResult.value = _userRepository.signUp(email, password, firstName, lastName)
        }
    }
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = _userRepository.login(email, password)
        }
    }
}