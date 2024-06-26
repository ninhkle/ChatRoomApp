package com.ninhkle.chatroom.data

sealed class Screen(val route : String) {
    data object LoginScreen : Screen("loginscreen")
    data object SignUpScreen : Screen("signupscreen")
    data object ChatRoomsScreen : Screen("chatroomscreen")
    data object ChatScreen : Screen("chatscreen")
}