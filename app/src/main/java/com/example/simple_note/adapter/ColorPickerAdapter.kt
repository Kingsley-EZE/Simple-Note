package com.example.simple_note.adapter
import android.content.Context
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.simple_note.databinding.ColorPickerItemBinding
import com.example.simple_note.util.ColorConstants.PREF_SELECTED_POSITION


class ColorPickerAdapter(
    private val colorList: List<Int>,
    private val context: Context,
    private var selectedPosition: Int = RecyclerView.NO_POSITION,
    private inline val onItemSelected: (Int) -> Unit = {},
) : RecyclerView.Adapter<ColorPickerAdapter.ColorViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ColorPickerAdapter.ColorViewHolder {
        return ColorViewHolder(
            ColorPickerItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ColorPickerAdapter.ColorViewHolder, position: Int) {
        holder.bind(colorList[position])
        holder.itemView.setOnClickListener {
            setSelectedPosition(position)
            onItemSelected(colorList[position])
            PreferenceManager.getDefaultSharedPreferences(context).edit().apply{
                putInt(PREF_SELECTED_POSITION, position)
                apply()
            }
        }
    }

    override fun getItemCount(): Int {
        return colorList.size
    }

    private fun setSelectedPosition(position: Int) {
        val previousSelectedPosition = selectedPosition
        selectedPosition = position
        if (previousSelectedPosition != RecyclerView.NO_POSITION) {
            notifyItemChanged(previousSelectedPosition)
        }
        notifyItemChanged(position)
    }

    inner class ColorViewHolder(private val binding: ColorPickerItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(color: Int){
            binding.item.setCardBackgroundColor(ContextCompat.getColor(context, color))
            binding.colorCheck.isVisible = adapterPosition == selectedPosition
        }
    }
}