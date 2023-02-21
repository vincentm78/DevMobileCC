package com.example.devmobilecc.presentation

import com.example.devmobilecc.data.Task

interface ITaskListener {
    fun deleteTask(task: Task)
    fun refreshData(data: List<Task>)
}