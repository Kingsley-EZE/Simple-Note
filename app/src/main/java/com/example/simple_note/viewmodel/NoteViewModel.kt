package com.example.simple_note.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simple_note.models.Note

class NoteViewModel : ViewModel() {

    private var _noteList = MutableLiveData<ArrayList<Note>>(ArrayList())
    val noteList : LiveData<ArrayList<Note>> get() = _noteList

    fun addNote(note: Note){
        _noteList.value?.add(note)
    }

}