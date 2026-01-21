package com.example.studentsapp

data class Student(
    var name: String,
    var id: String,
    var isChecked: Boolean = false,
    var imageResId: Int = R.drawable.ic_student
)