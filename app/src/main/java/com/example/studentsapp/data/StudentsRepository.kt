package com.example.studentsapp.data

import com.example.studentsapp.model.Student

object StudentsRepository {
    private val students = mutableListOf<Student>()

    fun getAll(): List<Student> = students

    fun add(student: Student) {
        students.add(student)
    }

    fun getByIndex(index: Int): Student? =
        students.getOrNull(index)

    fun update(index: Int, updated: Student) {
        if (index in students.indices) {
            students[index] = updated
        }
    }

    fun delete(index: Int) {
        if (index in students.indices) {
            students.removeAt(index)
        }
    }

    fun toggleChecked(index: Int) {
        val s = students.getOrNull(index) ?: return
        s.isChecked = !s.isChecked
    }
}
