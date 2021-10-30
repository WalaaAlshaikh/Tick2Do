package com.example.tick2do.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.tick2do.R
import com.example.tick2do.database.model.TodoInfo
import org.w3c.dom.Text


class DetailItemFragment : Fragment() {
    private val toDoViewModel:ToDoViewModel by activityViewModels()
    private lateinit var selectedItem:TodoInfo



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val taskNameTextView:TextView=view.findViewById(R.id.name_task_textView)
        val taskDescriptionTextView:TextView=view.findViewById(R.id.description_task_textview)
        val taskCreationDateTextView:TextView=view.findViewById(R.id.creation_Date_textview)
        val taskDueDateTextView:TextView=view.findViewById(R.id.due_date_textview)
        val taskStatus:TextView=view.findViewById(R.id.status_textview)
        val editButton:Button=view.findViewById(R.id.edit_button)
        val deleteButton:Button=view.findViewById(R.id.delete_button)


        toDoViewModel.selectedItemMutableLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {item ->
                taskNameTextView.text="Task: ${item.taskName}"
                taskDescriptionTextView.text="Description: ${item.description}"
                taskCreationDateTextView.text="Creation Date: ${item.creationDate}"
                taskDueDateTextView.text="Due Date: ${item.dueDate}"
                taskStatus.text="Task Status: ${item.isComplete}"
                selectedItem=item

            }
        })
        editButton.setOnClickListener {
            toDoViewModel.updateItems(selectedItem)
            findNavController().popBackStack()

        }
        deleteButton.setOnClickListener {
            toDoViewModel.deleteItem(selectedItem)
            findNavController().popBackStack()
        }



    }


}