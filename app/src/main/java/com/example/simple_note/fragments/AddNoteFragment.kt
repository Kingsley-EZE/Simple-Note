package com.example.simple_note.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.simple_note.R
import com.example.simple_note.databinding.FragmentAddNoteBinding


class AddNoteFragment : Fragment(){

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}