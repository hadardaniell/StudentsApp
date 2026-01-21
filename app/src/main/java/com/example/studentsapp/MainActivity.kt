package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.StudentRepository
import com.example.studentsapp.databinding.ActivityMainBinding
import com.example.studentsapp.StudentDetailsActivity
import com.example.studentsapp.ui.newstudent.NewStudentActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Initialize ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Setup the RecyclerView
        // We pass a lambda function that opens the Details screen when a row is clicked
        adapter = StudentAdapter { position ->
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("STUDENT_INDEX", position)
            startActivity(intent)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // 3. Setup the "Add Student" button
        binding.addStudentBtn.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // 4. Refresh the list every time we come back to this screen
        adapter.notifyDataSetChanged()
    }
}