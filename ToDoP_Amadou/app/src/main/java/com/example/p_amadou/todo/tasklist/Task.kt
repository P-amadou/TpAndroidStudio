package com.example.p_amadou.todo.tasklist

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import java.io.Serializable as Serial

@Serializable
data class Task(
        @SerialName("id")
        val id: String ,
        @SerialName("title")
        val title: String,
        @SerialName("description")
        val description: String ="default description" ) : Serial{}