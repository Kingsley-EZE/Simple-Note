package com.example.simple_note.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simple_note.R
import com.example.simple_note.databinding.NoteItemBinding
import com.example.simple_note.models.Note
import com.example.simple_note.models.Priority

class NotesAdapter(
    private val notes: ArrayList<Note>,
    private inline val onItemSelected: (Note) -> Unit = {}
) : RecyclerView.Adapter<NotesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(notes[position])
       holder.itemView.setOnClickListener {
           onItemSelected(notes[position])
       }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    inner class ViewHolder(private val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(note: Note){
            binding.noteItemTitle.text = note.noteTitle
            binding.noteItemContent.text = note.noteContent
            binding.editDate.text = note.lastEdited
            when(note.priority) {
                Priority.LOW -> binding.priorityTag.setImageResource(R.drawable.ic_priority_low)
                Priority.MEDIUM -> binding.priorityTag.setImageResource(R.drawable.ic_priority_medium)
                Priority.HIGH -> binding.priorityTag.setImageResource(R.drawable.ic_priority_high)
            }

        }
    }
}