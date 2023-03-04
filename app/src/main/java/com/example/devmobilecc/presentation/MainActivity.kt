package com.example.devmobilecc.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.devmobilecc.R
import com.example.devmobilecc.data.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), ITaskListener, IRefreshData {
    private lateinit var recycler: RecyclerView
    private lateinit var addButton: FloatingActionButton
    private lateinit var data: ArrayList<Task>
    private lateinit var adapter: TaskAdapter
    private var viewModel: ViewModel = ViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data = viewModel.getStoredTasks(this)
        recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(data, this)
        adapter.listener = this
        recycler.adapter = adapter
        addButton = findViewById<FloatingActionButton>(R.id.add_button)
        addButton.setOnClickListener {
            this.createTask()
        }

    }


    override fun refreshData(data: List<Task>) {
        this.data.clear()
        this.data.addAll(data)
        this.adapter.notifyDataSetChanged()
    }

    override fun checkBox(pos: Int) {
        data.get(pos).cocher = !data.get(pos).cocher
    }

    override fun editText(pos: Int, desc: String) {
        data.get(pos).description = desc
    }

    override fun deleteTask(task: Task) {
        viewModel.deleteTask(this, task)
    }

    /*
    override fun onStop() {
        super.onStop()

        viewModel.clearPreferences(this)
        for (task in data){
            viewModel.storeTask(this, task)
        }
    }
*/

    override fun onStop() {
        super.onStop()

        viewModel.clearPreferences(this)
        viewModel.writeListinPref(this, this.data)
    }


    private fun createTask() {
        var t = Task("", false)
        data.add(t)
        adapter = TaskAdapter(data, this)
        recycler.adapter = adapter

    }

    /*
    fun deleteTask(position: Int){
        data.removeAt(position)
        adapter = TaskAdapter(data, this)
        recycler.adapter = adapter
    }
     */
}