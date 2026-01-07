package com.example.studentsapp.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.R
import com.example.studentsapp.data.StudentsRepository
import com.example.studentsapp.databinding.ActivityStudentsListBinding
import com.example.studentsapp.model.Student
import com.example.studentsapp.ui.details.StudentDetailsActivity
import com.example.studentsapp.ui.newstudent.NewStudentActivity
import com.example.studentsapp.util.Extras

class StudentsListActivity : AppCompatActivity() {

    private lateinit var b: ActivityStudentsListBinding
    private val adapter = StudentsAdapter { index ->
        val i = Intent(this, StudentDetailsActivity::class.java)
        i.putExtra(Extras.STUDENT_INDEX, index)
        startActivity(i)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityStudentsListBinding.inflate(layoutInflater)
        setContentView(b.root)

        seedIfEmpty()

        b.rvStudents.layoutManager = LinearLayoutManager(this)
        b.rvStudents.adapter = adapter

        b.btnAdd.setOnClickListener {
            startActivity(Intent(this, NewStudentActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.submitList(StudentsRepository.getAll())
    }

    private fun seedIfEmpty() {
        if (StudentsRepository.getAll().isNotEmpty()) return
        StudentsRepository.add(Student("123", "Alice", false, R.drawable.ic_student))
        StudentsRepository.add(Student("456", "Bob", true, R.drawable.ic_student))
    }
}
