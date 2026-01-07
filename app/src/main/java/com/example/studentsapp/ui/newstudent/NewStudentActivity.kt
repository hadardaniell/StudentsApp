package com.example.studentsapp.ui.newstudent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.R
import com.example.studentsapp.data.StudentsRepository
import com.example.studentsapp.databinding.ActivityNewStudentBinding
import com.example.studentsapp.model.Student

class NewStudentActivity : AppCompatActivity() {

    private lateinit var b: ActivityNewStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityNewStudentBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnSave.setOnClickListener {
            val name = b.edtName.text.toString().trim()
            val id = b.edtId.text.toString().trim()

            if (name.isEmpty() || id.isEmpty()) {
                b.edtName.error = if (name.isEmpty()) "Required" else null
                b.edtId.error = if (id.isEmpty()) "Required" else null
                return@setOnClickListener
            }

            StudentsRepository.add(Student(id, name, false, R.drawable.ic_student))
            finish()
        }
    }
}
