package com.example.tick2do.todo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tick2do.database.model.TodoInfo
import com.example.tick2do.repositories.ToDoReop
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ToDoViewModel:ViewModel() {
    private var toDoRepository=ToDoReop.get()
    var doDoItems=toDoRepository.getItems()
    var uncompletedItem=toDoRepository.getUnCompletedItems()

    var selectedItemMutableLiveData= MutableLiveData<TodoInfo>()

    fun addItem(name:String,description:String,duedate:String,check:Boolean){

        viewModelScope.launch {
            val sdf=SimpleDateFormat("yyyy/M/dd")
            val currentDate=sdf.format(Date())
           toDoRepository.addItems(TodoInfo(name,check,duedate,description,currentDate))

       }
    }
    fun updateItems(todoitem: TodoInfo){
        viewModelScope.launch {

            toDoRepository.updateItems(todoitem)
        }

    }
    fun deleteItem(todoitem: TodoInfo){
        viewModelScope.launch {
            toDoRepository.deleteItems(todoitem)
        }
    }
}