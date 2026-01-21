package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.databinding.ActivityStudentDetailsBinding
import com.example.studentsapp.editstudent.EditStudentActivity

class StudentDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentDetailsBinding
    private var index: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        index = intent.getIntExtra("STUDENT_INDEX", -1)

        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("STUDENT_INDEX", index)
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        val student = StudentRepository.students.getOrNull(index)
        student?.let {
            binding.txtName.text = it.name
            binding.txtId.text = it.id
            binding.txtChecked.text = if (it.isChecked) "Checked" else "Unchecked"
        } ?: finish()
    }
}