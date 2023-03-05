package com.example.devmobilecc

import android.content.Context

class ViewModel {

    val repository = Repository()
    lateinit var listener: IRefreshData

    fun clearPreferences(context:Context){
        return repository.clearPreferences(context)
    }

    fun saveTasks(context: Context, tasks: ArrayList<Task>){
        repository.saveTasks(context, tasks)
        listener.refreshData(repository.getStoredTasks(context))
    }

    fun writeListinPref(context: Context, tasks:ArrayList<Task>){
        repository.writeListinPref(context, tasks)
        listener.refreshData(repository.getStoredTasks(context))
    }


    fun deleteTask(context: Context, task: Task){
        repository.deleteTask(context, task)
        listener.refreshData(repository.getStoredTasks(context))
    }

    fun getStoredTasks(context: Context): ArrayList<Task> {
        return this.repository.getStoredTasks(context)
    }

}