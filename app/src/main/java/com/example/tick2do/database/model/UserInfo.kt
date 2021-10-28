package com.example.tick2do.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfo(val name:String,val password:String,val email:String,@PrimaryKey(autoGenerate = true)
val id:Int=0 )
