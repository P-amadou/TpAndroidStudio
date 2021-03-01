package com.example.p_amadou.todo.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.p_amadou.todo.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class TaskListFragment : Fragment(){


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
            return inflater.inflate(R.layout.fragment_task_list, container, false)
            //val rootView = inflater.inflate(R.layout.fragment_task_list, container, false)

        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val fab=view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = TaskListAdapter(taskList)
        fab.setOnClickListener{
            val addTask = Task(id = UUID.randomUUID().toString(), title = "Task ${taskList.size + 1}")
            taskList.add(taskList.size,addTask)
        }
    }
}



