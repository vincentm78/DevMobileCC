package com.example.devmobilecc.presentation

import android.content.Context
import com.example.devmobilecc.data.Task
import com.example.devmobilecc.domain.Repository

class ViewModel {

    val repository = Repository()

    fun clearPreferences(context:Context){
        return repository.clearPreferences(context)
    }

    fun storeTask(context: Context, task:Task){
        repository.storeTask(context, task)
    }

    fun getStoredTasks(context: Context): ArrayList<Task> {
        return this.repository.getStoredTasks(context)
    }

}