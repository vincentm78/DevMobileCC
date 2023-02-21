package com.example.devmobilecc.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.devmobilecc.R
import com.example.devmobilecc.data.Task

class TaskAdapter(private val data: List<Task>): RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    lateinit var listener: ITaskListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.get(position)
        holder.taskTextView.text = item.nom
        holder.deleteButton.setOnClickListener {
            listener.deleteTask(item)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val taskTextView: TextView = view.findViewById(R.id.edit_name)
        val deleteButton: ImageButton = view.findViewById(R.id.ic_close)
    }
}