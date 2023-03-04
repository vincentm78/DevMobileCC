package com.example.devmobilecc.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.devmobilecc.R
import com.example.devmobilecc.data.Task

class TaskAdapter(private val data: List<Task>, private val act : MainActivity): RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    var listener: ITaskListener = act

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item_layout, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.get(position)
        holder.checkBox.isChecked = item.cocher
        holder.editTask.setText(item.description)
        holder.deleteButton.setOnClickListener{
            listener.deleteTask(item)
        }
        holder.checkBox.setOnClickListener{
            this.listener.checkBox(position)
        }
        holder.editTask.addTextChangedListener{
            this.listener.editText(holder.adapterPosition, holder.editTask.text.toString())
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val editTask: EditText = view.findViewById(R.id.edit_name)
        val deleteButton: ImageButton = view.findViewById(R.id.ic_close)
        val checkBox: CheckBox = view.findViewById(R.id.checkbox)
    }

    /*

    fun deleteTask(position: Int)
    {
        act.deleteTask(position)
    }


    */
}