package com.example.tick2do.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.example.tick2do.R


class AddItemFragment : Fragment() {

    private val toDoViewModel:ToDoViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addTaskEditText:EditText=view.findViewById(R.id.add_task_edittext)
        val addDescriptionEditText:EditText=view.findViewById(R.id.add_description_edittext)
        val addDueDateEditText:EditText=view.findViewById(R.id.add_date_edittext)
        val datePicker:DatePicker=view.findViewById(R.id.date_picker)
        val addTaskButton: Button =view.findViewById(R.id.add_item_button)

        addTaskButton.setOnClickListener {
            val taskName=addTaskEditText.text.toString()
            val description=addDescriptionEditText.text.toString()
            

        }

    }


}