package com.example.devmobilecc.presentation

import com.example.devmobilecc.data.Task

interface IRefreshListener {
    fun refreshData(data: List<Task>)
}