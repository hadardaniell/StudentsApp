package com.example.studentsapp.ui.details

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.data.StudentsRepository
import com.example.studentsapp.databinding.ActivityStudentDetailsBinding
//import com.example.studentsapp.ui.edit.EditStudentActivity
import com.example.studentsapp.util.Extras

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var b: ActivityStudentDetailsBinding
    private var index: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(b.root)

        index = intent.getIntExtra(Extras.STUDENT_INDEX, -1)

        b.btnEdit.setOnClickListener {
//            val i = Intent(this, EditStudentActivity::class.java)
//            i.putExtra(Extras.STUDENT_INDEX, index)
//            startActivity(i)
        }
    }

    override fun onResume() {
        super.onResume()
        val student = StudentsRepository.getByIndex(index) ?: run {
            finish()
            return
        }

        b.img.setImageResource(student.imageResId)
        b.txtName.text = student.name
        b.txtId.text = "ID: ${student.id}"
        b.txtChecked.text = "Checked: ${student.isChecked}"
    }
}
