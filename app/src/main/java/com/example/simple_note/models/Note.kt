package com.example.simple_note.models

data class Note(
    val noteTitle: String,
    val noteContent: String,
    val lastEdited: String,
    val noteColor: Int,
    val priority: Priority,
)

enum class Priority{
    LOW,
    MEDIUM,
    HIGH
}
