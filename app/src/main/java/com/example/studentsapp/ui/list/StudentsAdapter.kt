package com.example.studentsapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.data.StudentsRepository
import com.example.studentsapp.databinding.ItemStudentRowBinding
import com.example.studentsapp.model.Student

class StudentsAdapter(
    private val onRowClick: (index: Int) -> Unit
) : RecyclerView.Adapter<StudentsAdapter.VH>() {

    private var items: List<Student> = emptyList()

    fun submitList(newItems: List<Student>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class VH(val b: ItemStudentRowBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val b = ItemStudentRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(b)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val student = items[position]
        holder.b.txtName.text = student.name
        holder.b.txtId.text = "ID: ${student.id}"
        holder.b.imgStudent.setImageResource(student.imageResId)

        // חשוב: למנוע "קפיצות" בגלל recycled views
        holder.b.chk.setOnCheckedChangeListener(null)
        holder.b.chk.isChecked = student.isChecked

        holder.b.chk.setOnCheckedChangeListener { _, _ ->
            StudentsRepository.toggleChecked(position)
            notifyItemChanged(position)
        }

        holder.itemView.setOnClickListener { onRowClick(position) }
    }

    override fun getItemCount(): Int = items.size
}
