package com.example.devmobilecc.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.devmobilecc.R
import com.example.devmobilecc.data.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), ITaskListener, IRefreshListener {
    private lateinit var recycler: RecyclerView
    private lateinit var addButton: FloatingActionButton
    private lateinit var data: ArrayList<Task>
    private lateinit var adapter: TaskAdapter
    private var viewModel = ViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.listener = this
        data = viewModel.getStoredTasks(this)

        addButton = findViewById(R.id.add_button)
        recycler = findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(data)
        adapter.listener = this
        recycler.adapter = adapter

        addButton.setOnClickListener {
            //showDialog1()
            showDialog2()
        }
    }

    fun showDialog2() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.task_item_layout, null)
        val editText: EditText = layout.findViewById(R.id.edit_name)

        builder.apply {
            setTitle("Titre")
            setMessage("Texte")
            setPositiveButton("Ajouter") { _, _ ->
                try {
                    val person = viewModel.createTask(editText.text.toString())
                    viewModel.saveTask(this@MainActivity, person)
                    adapter.notifyDataSetChanged()
                } catch (e: java.lang.Exception) {
                    Toast.makeText(this@MainActivity, "Format invalide", Toast.LENGTH_SHORT).show()
                }
            }
            setNegativeButton("Annuler") { _, _ ->

            }
            setView(layout)
            show()
        }
    }

    override fun deleteTask(task: Task) {
        viewModel.deleteTask(this, task)
    }

    override fun refreshData(data: List<Task>) {
        this.data.clear()
        this.data.addAll(data)
        this.adapter.notifyDataSetChanged()
    }
}