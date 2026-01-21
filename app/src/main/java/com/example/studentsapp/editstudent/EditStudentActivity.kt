package com.example.studentsapp.editstudent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.StudentRepository
import com.example.studentsapp.databinding.ActivityEditStudentBinding

class EditStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditStudentBinding
    private var index: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        index = intent.getIntExtra("STUDENT_INDEX", -1)
        val student = StudentRepository.students.getOrNull(index)

        student?.let {
            binding.editName.setText(it.name)
            binding.editId.setText(it.id)
            binding.editCheckbox.isChecked = it.isChecked
        }

        binding.btnSave.setOnClickListener {
            student?.let {
                it.name = binding.editName.text.toString()
                it.id = binding.editId.text.toString()
                it.isChecked = binding.editCheckbox.isChecked
                StudentRepository.saveData(this) // SAVE CHANGES
            }
            finish()
        }

        binding.btnDelete.setOnClickListener {
            if (index != -1) {
                StudentRepository.students.removeAt(index)
                StudentRepository.saveData(this) // SAVE CHANGES
            }
            finish()
        }
        binding.btnCancel.setOnClickListener { finish() }
    }
}