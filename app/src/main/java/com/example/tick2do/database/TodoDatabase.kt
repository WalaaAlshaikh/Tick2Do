package com.example.tick2do.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tick2do.database.model.TodoInfo

@Database(entities = [TodoInfo::class],version = 1)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun todoDao():ToDoDao
}
