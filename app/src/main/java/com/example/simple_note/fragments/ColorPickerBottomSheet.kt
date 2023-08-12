package com.example.simple_note.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simple_note.R
import com.example.simple_note.adapter.ColorPickerAdapter
import com.example.simple_note.databinding.ColorPickerBottomSheetBinding
import com.example.simple_note.util.ColorConstants
import com.example.simple_note.util.ColorConstants.PREF_SELECTED_POSITION
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ColorPickerBottomSheet : BottomSheetDialogFragment(){

    private var _binding: ColorPickerBottomSheetBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ColorPickerBottomSheetBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PreferenceManager.getDefaultSharedPreferences(requireContext()).apply {
            val adapter = ColorPickerAdapter(ColorConstants.colorList, requireContext(), getInt(PREF_SELECTED_POSITION, RecyclerView.NO_POSITION)){
                val bundle = Bundle().apply {
                    putInt("colorCode", it)
                }
                navController.navigate(R.id.action_colorPickerBottomSheet_to_addNoteFragment, bundle)
            }
            binding.paletteRv.layoutManager = GridLayoutManager(requireContext(), 5)
            binding.paletteRv.adapter = adapter
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}