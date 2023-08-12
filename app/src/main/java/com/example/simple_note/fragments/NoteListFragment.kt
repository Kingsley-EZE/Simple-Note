package com.example.simple_note.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.simple_note.R
import com.example.simple_note.adapter.NotesAdapter
import com.example.simple_note.databinding.FragmentNoteListBinding
import com.example.simple_note.models.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class NoteListFragment : Fragment() {

    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val notes = listOf(
            Note(noteTitle = "This is a title1", noteContent = resources.getString(R.string.lorem), getFormattedDate()),
            Note(noteTitle = "This is a title2", noteContent = resources.getString(R.string.loremm), "10-08-22"),
            Note(noteTitle = "This is a title3", noteContent = resources.getString(R.string.loremm), "10-08-22"),
            Note(noteTitle = "This is a title4", noteContent = resources.getString(R.string.lorem), "10-08-22"),
        )

        val spanCount = 2
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
        binding.rvNote.layoutManager = staggeredGridLayoutManager

        val adapter = NotesAdapter(notes = notes){
            Toast.makeText(requireContext(), it.noteTitle, Toast.LENGTH_SHORT).show()
        }
        binding.rvNote.adapter = adapter

        binding.btnAddNote.setOnClickListener {
            navController.navigate(R.id.action_noteListFragment_to_addNoteFragment)
        }

    }

    private fun getFormattedDate(): String {
        val dateFormat = SimpleDateFormat("dd-MM-yy", Locale.getDefault())
        val currentDate = Date()
        return dateFormat.format(currentDate)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}