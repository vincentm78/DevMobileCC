package com.example.devmobilecc.presentation

import com.example.devmobilecc.data.Task

interface ITaskListener {

    fun checkBox(pos: Int)
    fun editText(pos: Int, desc: String)
    fun deleteTask(task: Task)

}
