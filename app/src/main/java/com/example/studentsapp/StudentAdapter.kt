package com.example.studentsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.databinding.StudentRowItemBinding

class StudentAdapter(private val onRowClick: (Int) -> Unit) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var studentsList: List<Student> = listOf()

    class StudentViewHolder(val binding: StudentRowItemBinding) : RecyclerView.ViewHolder(binding.root)

    fun submitList(newList: List<Student>) {
        // Create a copy of the list to ensure the adapter detects changes correctly
        this.studentsList = ArrayList(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentRowItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentsList[position]

        // 1. Set text and image data
        holder.binding.studentName.text = student.name
        holder.binding.studentId.text = student.id
        holder.binding.studentImage.setImageResource(student.imageResId)

        // 2. Handle the Checkbox
        // We set the listener to null first to avoid triggering a 'save'
        // while the RecyclerView is recycling the view
        holder.binding.studentCheckbox.setOnCheckedChangeListener(null)
        holder.binding.studentCheckbox.isChecked = student.isChecked

        holder.binding.studentCheckbox.setOnCheckedChangeListener { _, isChecked ->
            student.isChecked = isChecked
            // Save the new checkbox state to the phone's memory immediately
            StudentRepository.saveData(holder.itemView.context)
        }

        // 3. Handle Row Click (Go to Details)
        holder.itemView.setOnClickListener {
            onRowClick(position)
        }
    }

    override fun getItemCount() = studentsList.size
}