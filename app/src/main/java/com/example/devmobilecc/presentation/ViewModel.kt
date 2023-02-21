package com.example.devmobilecc.presentation

import android.content.Context
import com.example.devmobilecc.data.Task
import com.example.devmobilecc.domain.Repository

class ViewModel {

    val repository = Repository()


    fun getStoredTasks(context: Context): ArrayList<Task> {
        return repository.getStoredTasks(context)
    }

    fun saveTask(context: Context, Task: Task) {
        repository.saveTask(context, Task)
        listener.refreshData(repository.getStoredTasks(context))
    }

    fun deleteTask(context: Context, Task: Task) {
        repository.deleteTask(context, Task)
        listener.refreshData(repository.getStoredTasks(context))
    }

    fun createTask(text: String): Task {
        val split = text.split(",")
        if (split.size == 2) {
            return Task(
                first_name = split[1].trim(),
            )
        } else if (text.trim().isNotEmpty()) {
            return Task(
                first_name = "",
                last_name = text
            )
        }
    }
}