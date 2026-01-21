package com.example.studentsapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// MAKE SURE THIS NAME IS UNIQUE IN YOUR WHOLE PROJECT
object StudentRepository {
    private const val PREFS_NAME = "student_prefs"
    private const val KEY_STUDENTS = "students_list"

    var students = mutableListOf<Student>()

    fun init(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_STUDENTS, null)

        if (json != null) {
            val type = object : TypeToken<MutableList<Student>>() {}.type
            students = Gson().fromJson(json, type)
        } else {
            saveData(context)
        }
    }

    fun saveData(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val json = Gson().toJson(students)
        editor.putString(KEY_STUDENTS, json)
        editor.apply()
    }
}