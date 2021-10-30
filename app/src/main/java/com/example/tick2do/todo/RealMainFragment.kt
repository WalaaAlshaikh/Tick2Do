package com.example.tick2do.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tick2do.R
import com.example.tick2do.database.model.TodoInfo
import com.google.android.material.floatingactionbutton.FloatingActionButton


class RealMainFragment : Fragment() {
    private val todoItemsList= mutableListOf<TodoInfo>()
    private val toDoViewModel:ToDoViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_real_main, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val todoRecyclerView:RecyclerView=view.findViewById(R.id.todo_recyclerView)
        val addFloatingButton:FloatingActionButton=view.findViewById(R.id.add_floating_button)
        val toDoAdapter=ToDoAdapter(todoItemsList,toDoViewModel)

        todoRecyclerView.adapter=toDoAdapter

        toDoViewModel.doDoItems.observe(viewLifecycleOwner, Observer {
            it?.let { items ->
                todoItemsList.clear()
                todoItemsList.addAll(items)
                val linearLout: LinearLayout = LinearLayout(context)
                linearLout.setVisibility(View.INVISIBLE)
                toDoAdapter.notifyDataSetChanged()

            }
        })

        addFloatingButton.setOnClickListener {
            findNavController().navigate(R.id.action_realMainFragment_to_addItemFragment)

        }

    }


}