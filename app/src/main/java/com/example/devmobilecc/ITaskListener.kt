package com.example.devmobilecc

interface ITaskListener {

    fun checkBox(pos: Int)
    fun editText(pos: Int, desc: String)
    fun deleteTask(task: Task)

}
