package com.example.devmobilecc

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MainActivity : AppCompatActivity(), ITaskListener, IRefreshData {
    private lateinit var recycler: RecyclerView
    private lateinit var addButton: FloatingActionButton
    private lateinit var data: ArrayList<Task>
    private lateinit var adapter: TaskAdapter
    private var viewModel: ViewModel = ViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data.addAll(getStoredTasks())
        recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(data, this)
        adapter.listener = this
        recycler.adapter = adapter
        addButton = findViewById<FloatingActionButton>(R.id.add_button)
        addButton.setOnClickListener {
            this.createTask()
        }

    }


    override fun refreshData(data: List<Task>) {
        this.data.clear()
        this.data.addAll(data)
        this.adapter.notifyDataSetChanged()
    }

    override fun checkBox(pos: Int) {
        data.get(pos).cocher = !data.get(pos).cocher
    }

    override fun editText(pos: Int, desc: String) {
        data.get(pos).description = desc
    }


    override fun onStop() {
        super.onStop()

        saveTasks(data)
    }


    private fun createTask() {
        var t = Task("", false)
        data.add(t)
        adapter = TaskAdapter(data, this)
        recycler.adapter = adapter

    }

    fun getStoredTasks(): ArrayList<Task> {
        val preferences = this.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val TasksStr = preferences.getString("Tasks", "")
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<Task?>?>() {}.type
        try {
            return gson.fromJson<Any>(TasksStr, type) as ArrayList<Task>
        } catch (e: NullPointerException) { return ArrayList<Task>() }
    }


    fun saveTasks(tasks: ArrayList<Task>) {
        val preferences = this.getSharedPreferences("preferences", Context.MODE_PRIVATE)
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

    override fun deleteTask(Task: Task) {
        val preferences = this.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        val TasksStr = preferences.getString("tasks", "")
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<Task?>?>() {}.type
        var tasks = ArrayList<Task>()
        try {
            tasks = gson.fromJson<Any>(TasksStr, type) as ArrayList<Task>
            tasks.remove(Task)
            editor.putString("tasks", gson.toJson(tasks))
            editor.apply()
        } catch (e: NullPointerException) { e.printStackTrace() }
    }

    fun clearPreferences(){
        val preferences = this.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val editor = preferences.edit().clear()
        editor.commit()
    }

    /*
    fun deleteTask(position: Int){
        data.removeAt(position)
        adapter = TaskAdapter(data, this)
        recycler.adapter = adapter
    }
     */
}