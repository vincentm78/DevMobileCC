package com.example.devmobilecc.presentation

import android.content.Context
import com.example.devmobilecc.data.Task
import com.example.devmobilecc.domain.Repository

class ViewModel {

    val repository = Repository()
    lateinit var listener: IRefreshListener


    fun getStoredTasks(context: Context): ArrayList<Task> {
        return this.repository.getStoredTasks(context)
    }

    fun saveTask(context: Context, Task: Task) {
        this.repository.saveTask(context, Task)
        this.listener.refreshData(repository.getStoredTasks(context))
    }

    fun deleteTask(context: Context, Task: Task) {
        this.repository.deleteTask(context, Task)
        this.listener.refreshData(repository.getStoredTasks(context))
    }

    fun createTask(text: String): Task {
        var task: Task = Task()
        task.nom = text
        return task
    }
}