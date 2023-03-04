package com.example.devmobilecc.presentation

import com.example.devmobilecc.data.Task

interface IRefreshData {

    fun refreshData(data: List<Task>)
}