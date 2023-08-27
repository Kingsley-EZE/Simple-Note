package com.example.simple_note.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.simple_note.R
import com.example.simple_note.databinding.FragmentAddNoteBinding
import com.example.simple_note.models.Note
import com.example.simple_note.models.Priority
import com.example.simple_note.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class AddNoteFragment : Fragment(){

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel: NoteViewModel by activityViewModels()
    private var notePriority = Priority.LOW

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedColor = arguments?.getInt("colorCode")

        binding.root.setBackgroundColor(
            ContextCompat.getColor(requireActivity(), selectedColor ?: R.color.white)
        )

        binding.priorityGroup?.setOnCheckedChangeListener { _, checkedId ->
            val selectedButton = view.findViewById<AppCompatRadioButton>(checkedId)
            when(selectedButton.id){
                R.id.btn_low -> notePriority = Priority.LOW
                R.id.btn_medium -> notePriority = Priority.MEDIUM
                R.id.btn_high -> notePriority = Priority.HIGH
            }
        }

        setUpClickListeners()
    }

    private fun setUpClickListeners(){
        binding.apply {
            btnPalette.setOnClickListener {
                navController.navigate(R.id.action_addNoteFragment_to_colorPickerBottomSheet)
            }
            btnBack.setOnClickListener {
                navController.navigate(R.id.action_addNoteFragment_to_noteListFragment)
            }
            btnSave.setOnClickListener {
                addNewNote()
                navController.navigate(R.id.action_addNoteFragment_to_noteListFragment)
            }
        }
    }

    private fun addNewNote(){
        val noteTitle = binding.noteTitle.text.toString().trim()
        val noteContent = binding.note.text.toString().trim()
        val noteColor = 0
        val date = getFormattedDate()
        val newNote = Note(noteTitle = noteTitle, noteContent = noteContent, noteColor = noteColor, lastEdited = date, priority = notePriority)
        viewModel.addNote(newNote)
    }

    private fun getFormattedDate(): String {
        val dateFormat = SimpleDateFormat("dd-MM-yy HH:mm", Locale.getDefault())
        val currentDate = Date()
        return dateFormat.format(currentDate)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}