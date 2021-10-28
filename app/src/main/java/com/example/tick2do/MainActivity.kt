package com.example.tick2do

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tick2do.repositories.ToDoReop

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ToDoReop.init(this)
    }
}