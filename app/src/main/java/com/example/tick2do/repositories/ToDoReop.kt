package com.example.tick2do.repositories

import android.content.Context
import androidx.room.Room
import com.example.tick2do.database.TodoDatabase
import com.example.tick2do.database.model.TodoInfo
import java.lang.Exception

private const val DATABASE_NAME="todolist-database"

class ToDoReop (context:Context) {
    private val database: TodoDatabase = Room.databaseBuilder(context,TodoDatabase::class.java,
        DATABASE_NAME).fallbackToDestructiveMigration().build()
    private val toDoDao=database.todoDao()
    fun getItems()=toDoDao.getItem(true)
    fun getUnCompletedItems()=toDoDao.getItem(false)
    suspend fun addItems (todoitem:TodoInfo)=toDoDao.addItem(todoitem)
    suspend fun updateItems (todoitem:TodoInfo)=toDoDao.updateItem(todoitem)
    suspend fun deleteItems (todoitem:TodoInfo)=toDoDao.deleteItem(todoitem)

    companion object {
        private var instance:ToDoReop?=null

        fun init(context: Context){
            if(instance==null)
                instance= ToDoReop(context)
        }

        fun get():ToDoReop{
            return instance?:throw Exception("To do Repository must be initialized")
        }

    }
}