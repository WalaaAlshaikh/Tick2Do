package com.example.tick2do.todo

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.tick2do.R
import com.example.tick2do.database.model.TodoInfo
import org.w3c.dom.Text
import java.util.*

private const val TAG="Fragment"
class DetailItemFragment : Fragment() {
    private val toDoViewModel:ToDoViewModel by activityViewModels()
    private lateinit var selectedItem:TodoInfo
    private lateinit var mDateSet:DatePickerDialog.OnDateSetListener



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val taskNameTextView:EditText=view.findViewById(R.id.taskname_edittext)
        val taskDescriptionTextView:EditText=view.findViewById(R.id.taskDescription_editText)
        val taskCreationDateTextView:TextView=view.findViewById(R.id.creation_Date_textview)
        val taskDueDateTextView:EditText=view.findViewById(R.id.taskDueDate_EditText)
        val taskStatus:TextView=view.findViewById(R.id.status_textview)
        val editButton:Button=view.findViewById(R.id.edit_button)
        val deleteButton:Button=view.findViewById(R.id.delete_button)


        toDoViewModel.selectedItemMutableLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {item ->
                taskNameTextView.setText(item.taskName)
                taskDescriptionTextView.setText(item.description)
                taskCreationDateTextView.text=item.creationDate
                taskDueDateTextView.setText(item.dueDate)
                taskStatus.text=item.isComplete.toString()
                if (item.isComplete == true){
                    taskStatus.text="Completed"
                }else{
                    taskStatus.text="Uncompleted"
                }
                taskStatus.setOnClickListener {
                    Toast.makeText(context, "You can change the status from the check box in the main page", Toast.LENGTH_LONG).show()
                }
                taskCreationDateTextView.setOnClickListener {
                    Toast.makeText(context, "Creation date can not be edited once you created the task", Toast.LENGTH_LONG).show()

                }
                taskDueDateTextView.setOnClickListener{
                    val calender= Calendar.getInstance()
                    val year:Int=calender.get(Calendar.YEAR)
                    val month:Int=calender.get(Calendar.MONTH)
                    val day:Int=calender.get(Calendar.DAY_OF_MONTH)
                    val dailoge: DatePickerDialog = DatePickerDialog(requireContext(),android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSet,year,month,day)
                    dailoge.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    dailoge.show()


                }

                mDateSet= DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                    var month = month
                    month = month + 1
                    Log.d(TAG, "onDateSet: dd/mm/yyy: $day/$month/$year")
                    val date = "$day/$month/$year"
                    taskDueDateTextView.setText(date)
                }
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