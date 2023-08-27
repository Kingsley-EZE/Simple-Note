package com.example.simple_note.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.simple_note.R
import com.example.simple_note.adapter.NotesAdapter
import com.example.simple_note.databinding.FragmentNoteListBinding
import com.example.simple_note.viewmodel.NoteViewModel


class NoteListFragment : Fragment() {

    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel: NoteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.noteList.observe(viewLifecycleOwner){ notes ->
            if (notes.isEmpty()){
                binding.emptyNoteTag?.isVisible = true
            }else{
                binding.emptyNoteTag?.isVisible = false
                val adapter = NotesAdapter(notes = notes){
                    Toast.makeText(requireContext(), it.noteTitle, Toast.LENGTH_SHORT).show()
                }
                binding.rvNote.adapter = adapter
            }
        }

        val spanCount = 2
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
        binding.rvNote.layoutManager = staggeredGridLayoutManager

        binding.btnAddNote.setOnClickListener {
            navController.navigate(R.id.action_noteListFragment_to_addNoteFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}