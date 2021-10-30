package com.example.tick2do.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class TodoInfo(val taskName:String ,
                    var isComplete:Boolean,
                    val dueDate:String,
                    val description:String,
                    val creationDate: String,
                    @PrimaryKey(autoGenerate = true)val id:Int=0)
