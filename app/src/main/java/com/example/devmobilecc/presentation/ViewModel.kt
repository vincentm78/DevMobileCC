package com.example.devmobilecc.presentation

import android.content.Context
import com.example.devmobilecc.data.Task
import com.example.devmobilecc.domain.Repository

class ViewModel {

    val repository = Repository()
    lateinit var listener: IRefreshData

    fun clearPreferences(context:Context){
        return repository.clearPreferences(context)
    }
/*
    fun storeTask(context: Context, task:Task){
        repository.storeTask(context, task)
        listener.refreshData(repository.getStoredTasks(context))
    }
*/
    fun writeListinPref(context: Context, tasks:ArrayList<Task>){
        repository.writeListinPref(context, tasks)
        listener.refreshData(repository.readListFromPref(context))
    }


    fun deleteTask(context: Context, task: Task){
        repository.deleteTask(context, task)
        listener.refreshData(repository.readListFromPref(context))
    }

    fun getStoredTasks(context: Context): ArrayList<Task> {
        return this.repository.readListFromPref(context)
    }

}