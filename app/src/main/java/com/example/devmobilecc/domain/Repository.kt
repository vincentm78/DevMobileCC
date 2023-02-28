package com.example.devmobilecc.domain

import android.content.Context
import com.example.devmobilecc.data.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Repository {
    fun getMockTasks() : ArrayList<Task> {
        var Tasks = ArrayList<Task>()
        //Tasks.add(Task(first_name = "Walter", last_name = "White"))
        return Tasks
    }

    fun getStoredTasks(context: Context): ArrayList<Task> {
        val preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val TasksStr = preferences.getString("Tasks", "")
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<Task?>?>() {}.type
        try {
            return gson.fromJson<Any>(TasksStr, type) as ArrayList<Task>
        } catch (e: NullPointerException) { return ArrayList<Task>() }
    }

    fun saveTask(context: Context, Task: Task) {
        val preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        val TasksStr = preferences.getString("Tasks", "")
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<Task?>?>() {}.type
        var Tasks = ArrayList<Task>()
        try {
            Tasks = gson.fromJson<Any>(TasksStr, type) as ArrayList<Task>
        } catch (e: NullPointerException) { e.printStackTrace() }
        Tasks.add(Task)
        editor.putString("Tasks", gson.toJson(Tasks))
        editor.apply()
    }

    fun deleteTask(context: Context, Task: Task) {
        val preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        val TasksStr = preferences.getString("Tasks", "")
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<Task?>?>() {}.type
        var Tasks = ArrayList<Task>()
        try {
            Tasks = gson.fromJson<Any>(TasksStr, type) as ArrayList<Task>
            Tasks.remove(Task)
            editor.putString("Tasks", gson.toJson(Tasks))
            editor.apply()
        } catch (e: NullPointerException) { e.printStackTrace() }
    }
}