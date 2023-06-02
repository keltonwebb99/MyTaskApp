package com.example.myapp

//Imports necessary packages for handling view components, activity lifecycle and interactions, and RecyclerView features.
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//The main activity class for the application. It extends AppCompatActivity, which is a base class for activities that use modern Android features and Material Design.
class MainActivity : AppCompatActivity() {
    //Define the task list and the adapter for the RecyclerView
    private lateinit var taskList: ArrayList<String>
    private lateinit var adapter: TaskAdapter

    //Override the onCreate method which is called when the activity is first created.
    override fun onCreate(savedInstanceState: Bundle?) {
        //Call the onCreate method of the superclass and set the layout of the activity.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Find and initialize views within the activity layout
        val editTextTask = findViewById<EditText>(R.id.editTextTask)
        val buttonAddTask = findViewById<Button>(R.id.buttonAddTask)
        val recyclerViewTasks = findViewById<RecyclerView>(R.id.recyclerViewTasks)

        //Initialize task list and adapter, then set the RecyclerView's layout manager and adapter.
        taskList = ArrayList()
        adapter = TaskAdapter(taskList)
        recyclerViewTasks.layoutManager = LinearLayoutManager(this)
        recyclerViewTasks.adapter = adapter

        //Define what happens when the button is clicked - it retrieves text from the EditText, adds it to the task list, and updates the RecyclerView.
        buttonAddTask.setOnClickListener {
            val task = editTextTask.text.toString()
            if (task.isNotEmpty()) {
                taskList.add(task)
                adapter.notifyItemInserted(taskList.size - 1)
                editTextTask.text.clear()
            }
        }
    }
}

//Define the adapter for the RecyclerView, which handles the presentation of the tasks in the RecyclerView.
class TaskAdapter(private val taskList: ArrayList<String>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    //Define the ViewHolder for the RecyclerView items - it binds the views within each item to a variable.
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(android.R.id.text1)
    }

    //Override the onCreateViewHolder method to inflate the view for each item and return a ViewHolder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return TaskViewHolder(itemView)
    }

    //Override the onBindViewHolder method to set the text of the TextView in each item to the corresponding task.
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = taskList[position]
        holder.textView.text = currentTask
    }

    //Override the getItemCount method to return the size of the task list.
    override fun getItemCount() = taskList.size
}
