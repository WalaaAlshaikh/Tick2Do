package com.example.tick2do.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tick2do.database.model.TodoInfo

@Dao
interface ToDoDao {
    @Insert
    suspend fun addItem(todoitem:TodoInfo)
    @Query("SELECT * FROM todoinfo")
    fun getItem():LiveData<List<TodoInfo>>
    @Update
    suspend fun updateItem(todoitem: TodoInfo)
    @Delete
    suspend fun deleteItem(todoitem: TodoInfo)

}