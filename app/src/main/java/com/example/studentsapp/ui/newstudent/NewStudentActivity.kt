package com.example.studentsapp.ui.newstudent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.Student
import com.example.studentsapp.StudentRepository
import com.example.studentsapp.databinding.ActivityNewStudentBinding

class NewStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val name = binding.edtName.text.toString()
            val id = binding.edtId.text.toString()

            if (name.isNotBlank() && id.isNotBlank()) {
                StudentRepository.students.add(Student(name, id, false, R.drawable.ic_student))

                // PERSIST DATA
                StudentRepository.saveData(this)

                finish()
            }
        }
        binding.btnCancel.setOnClickListener { finish() }
    }
}