package com.ninhkle.chatroom.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatRoomListScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "Chat Rooms", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(content = {})
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Create Room")
        }
        if (showDialog) {
            AlertDialog(onDismissRequest = { showDialog = true },
                title = { Text(text = "Create a new room")},
                text ={
                      OutlinedTextField(value = name,
                          onValueChange = { name = it },
                          singleLine = true,
                          modifier = Modifier
                              .fillMaxWidth()
                              .padding(8.dp)
                      )
                },
                confirmButton = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Button(onClick = {
                            if (name.isNotBlank()) {
                                showDialog = false
                            }
                        }) {
                            Text(text = "Add")
                        }
                        Button(onClick = {
                            showDialog = false
                        }) {
                            Text(text = "Cancel")
                        }
                    }
                })
        }
    }
}

@Composable
fun RoomItem(/*room: Room*/) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "room.name", fontSize = 16.sp, fontWeight = FontWeight.Normal)
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(text = "Join")
        }

    }
}

@Preview
@Composable
fun RoomItemPreview() {
    RoomItem()
}

@Preview
@Composable
fun ChatRoomListScreenPreview() {
    ChatRoomListScreen()
}