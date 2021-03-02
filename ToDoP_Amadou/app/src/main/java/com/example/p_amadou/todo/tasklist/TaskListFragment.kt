package com.example.p_amadou.todo.tasklist

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.p_amadou.todo.R
import com.example.p_amadou.todo.databinding.FragmentTaskListBinding
import com.example.p_amadou.todo.task.TaskActivity
import com.example.p_amadou.todo.task.TaskActivity.Companion.ADD_TASK_REQUEST_CODE
import com.example.p_amadou.todo.task.TaskActivity.Companion.EDIT_TASK_REQUEST_CODE
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class TaskListFragment : Fragment(){
    private lateinit var viewBinding : FragmentTaskListBinding
    private var adapter : TaskListAdapter = TaskListAdapter()
    private val taskList = mutableListOf(
                Task(id = "id_1", title = "Task 1", description = "description 1"),
                Task(id = "id_2", title = "Task 2"),
                Task(id = "id_3", title = "Task 3")
        )

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?

        ): View? {
        viewBinding = FragmentTaskListBinding.inflate(inflater, container, false)
        return viewBinding.root


        }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(activity)
        viewBinding.recyclerView.adapter = adapter
        viewBinding.floatingActionButton.setOnClickListener{

            val addTask = Task(id = UUID.randomUUID().toString(), title = "Task ${taskList.size + 1}")
            taskList.add(taskList.size,addTask)
            val intent = Intent(activity, TaskActivity::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST_CODE)
            adapter.submitList(taskList.toList())
        }
        adapter.onDeleteTask = { task ->
            taskList.remove(task)
            adapter.submitList(taskList.toList())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == ADD_TASK_REQUEST_CODE) {
            val task=data?.getSerializableExtra(TaskActivity.TASK_KEY) as? Task
        if (task != null) {
            taskList.add(taskList.size, task)
            adapter.submitList(taskList.toList())
        }
    } else if (resultCode == RESULT_OK && requestCode == EDIT_TASK_REQUEST_CODE) {
        val task = data?.getSerializableExtra(TaskActivity.TASK_KEY) as? Task
        if (task != null) {
            val position = taskList.indexOfFirst { it -> it.id == task.id }
            taskList[position] = task
            adapter.submitList(taskList.toList())
        }
    }
    }
}




