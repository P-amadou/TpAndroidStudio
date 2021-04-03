package com.example.p_amadou.todo.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.p_amadou.todo.tasklist.Task

class TasksRepository {
    private val tasksWebService = Api.tasksWebService
    private val webService = Api.tasksWebService

    // Ces deux variables encapsulent la même donnée:
    // [_taskList] est modifiable mais privée donc inaccessible à l'extérieur de cette classe
    private val _taskList = MutableLiveData<List<Task>>()
    // [taskList] est publique mais non-modifiable:
    // On pourra seulement l'observer (s'y abonner) depuis d'autres classes
    public val taskList: LiveData<List<Task>> = _taskList

    suspend fun refresh() {
        // Call HTTP (opération longue):
        val tasksResponse = tasksWebService.getTasks()
        // À la ligne suivante, on a reçu la réponse de l'API:
        if (tasksResponse.isSuccessful) {
            val fetchedTasks = tasksResponse.body()
            // on modifie la valeur encapsulée, ce qui va notifier ses Observers et donc déclencher leur callback
            _taskList.value = fetchedTasks!!
        }
    }

    suspend fun delete(id: Task): Unit? {
        val deleteTasksResponse = tasksWebService.deleteTask(id.toString())
        /*if (tasksResponse.isSuccessful) {
            val editableList = _taskList.value.orEmpty().toMutableList()
            val position = editableList.indexOfFirst { it.id == id }
            if (position != -1) {
                editableList.removeAt(position)
                _taskList.value = editableList
            }
        }*/
        return if(deleteTasksResponse.isSuccessful) deleteTasksResponse.body() else null
    }

    suspend fun create(task: Task): Task? {
        val createTasksResponse = tasksWebService.createTask(task)
        /*if (tasksResponse.isSuccessful) {
            val editableList = _taskList.value.orEmpty().toMutableList()
            editableList.add(editableList.size, task)
            _taskList.value = editableList
        }*/
        return if(createTasksResponse.isSuccessful) createTasksResponse.body() else null

    }

    suspend fun updateTask(task: Task): Task? {
        val updateTasksResponse = tasksWebService.updateTask(task, task.id)
        /*if (tasksResponse.isSuccessful) {
            val updatedTask : Task? = tasksResponse.body()
            val editableList = _taskList.value.orEmpty().toMutableList()
            val position = editableList.indexOfFirst { updatedTask?.id == it.id }
            if (updatedTask != null) {
                editableList[position] = updatedTask
            }
            _taskList.value = editableList
        }*/
        return if(updateTasksResponse.isSuccessful) updateTasksResponse.body() else null

    }
    suspend fun removeTask(task: Task): Unit? {
        val deleteResponse = tasksWebService.deleteTask(task.id)
//        val editableList = _taskList.value.orEmpty().toMutableList()
//        editableList.remove(task)
//        _taskList.value = editableList
        return if(deleteResponse.isSuccessful) deleteResponse.body() else null
        }

    suspend fun loadTasks(): List<Task>? {
        val response = webService.getTasks()
        return if (response.isSuccessful) response.body() else null
    }

}


