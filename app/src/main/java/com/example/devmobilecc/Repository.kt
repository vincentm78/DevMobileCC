package com.example.devmobilecc

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Repository {

    private val LIST_KEY = "list_key"

    fun getStoredTasks(context: Context): ArrayList<Task> {
        val preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val TasksStr = preferences.getString("Tasks", "")
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<Task?>?>() {}.type
        try {
            return gson.fromJson<Any>(TasksStr, type) as ArrayList<Task>
        } catch (e: NullPointerException) { return ArrayList<Task>() }
    }


    fun saveTasks(context: Context, tasks: ArrayList<Task>) {
        val preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        val TasksStr = preferences.getString("tasks", "")
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<Task?>?>() {}.type
        var tasksList = ArrayList<Task>()
        try {
            tasksList = gson.fromJson<Any>(TasksStr, type) as ArrayList<Task>
        } catch (e: NullPointerException) { e.printStackTrace() }
        tasksList.addAll(tasks)
        editor.putString("tasks", gson.toJson(tasks))
        editor.apply()
    }

    fun deleteTask(context: Context, Task: Task) {
        val preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        val TasksStr = preferences.getString(LIST_KEY, "")
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<Task?>?>() {}.type
        var Tasks = ArrayList<Task>()
        try {
            Tasks = gson.fromJson<Any>(TasksStr, type) as ArrayList<Task>
            Tasks.remove(Task)
            editor.putString(LIST_KEY, gson.toJson(Tasks))
            editor.apply()
        } catch (e: NullPointerException) { e.printStackTrace() }
    }


    fun storeTask(context: Context, task: Task){
        val preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        val taskStr = preferences.getString(LIST_KEY, "")
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<Task?>?>(){}.type
        var Tasks = ArrayList<Task>()
        try{
            Tasks=gson.fromJson<Any>(taskStr, type) as ArrayList<Task>
            Tasks.add(task)
            editor.putString(LIST_KEY, gson.toJson(Tasks))
            editor.apply()
        }catch(e: NullPointerException){e.printStackTrace()}

    }



    fun writeListinPref(context: Context, list: ArrayList<Task>) {
        val gson = Gson()
        val jsonString = gson.toJson(list)
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = pref.edit()
        editor.putString(LIST_KEY, jsonString)
        editor.apply()
    }

    fun readListFromPref(context: Context): ArrayList<Task> {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val jsonString = pref.getString(LIST_KEY, "")
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Task?>?>() {}.type
        val list = gson.fromJson<ArrayList<Task>>(jsonString, type)
        val list2: ArrayList<Task> = ArrayList()
        return list ?: list2
    }


    fun clearPreferences(context: Context){
        val preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val editor = preferences.edit().clear()
        editor.commit()
    }
}