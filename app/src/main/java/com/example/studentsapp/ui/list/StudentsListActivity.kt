package com.example.studentsapp.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.StudentAdapter
import com.example.studentsapp.StudentDetailsActivity
import com.example.studentsapp.StudentRepository
import com.example.studentsapp.databinding.ActivityStudentsListBinding
import com.example.studentsapp.ui.newstudent.NewStudentActivity

class StudentsListActivity : AppCompatActivity() {
    private lateinit var b: ActivityStudentsListBinding
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // LOAD SAVED DATA
        StudentRepository.init(this)

        b = ActivityStudentsListBinding.inflate(layoutInflater)
        setContentView(b.root)

        adapter = StudentAdapter { index ->
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("STUDENT_INDEX", index)
            startActivity(intent)
        }

        b.rvStudents.layoutManager = LinearLayoutManager(this)
        b.rvStudents.adapter = adapter

        b.btnAdd.setOnClickListener {
            startActivity(Intent(this, NewStudentActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.submitList(StudentRepository.students)
    }
}