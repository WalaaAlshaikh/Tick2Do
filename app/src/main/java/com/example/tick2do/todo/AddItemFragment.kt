package com.example.tick2do.todo

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tick2do.R
import java.time.Month
import java.time.Year
import java.util.*

private const val TAG="Fragment"
class AddItemFragment : Fragment() {

    private val toDoViewModel:ToDoViewModel by activityViewModels()
    private lateinit var mDateSet:DatePickerDialog.OnDateSetListener



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addTaskEditText:EditText=view.findViewById(R.id.add_task_edittext)
        val addDescriptionEditText:EditText=view.findViewById(R.id.add_description_edittext)
        val addDueDateEditText:EditText=view.findViewById(R.id.add_date_edittext)
        val datePicker:DatePicker=view.findViewById(R.id.date_picker)
        val addTaskButton: Button =view.findViewById(R.id.add_item_button)


        addDueDateEditText.setOnClickListener{
            val calender=Calendar.getInstance()
            val year:Int=calender.get(Calendar.YEAR)
            val month:Int=calender.get(Calendar.MONTH)
            val day:Int=calender.get(Calendar.DAY_OF_MONTH)
            val dailoge:DatePickerDialog= DatePickerDialog(requireContext(),android.R.style.Theme_Holo_Dialog_MinWidth,
            mDateSet,year,month,day)
            dailoge.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dailoge.show()


                }

        mDateSet= OnDateSetListener { datePicker, year, month, day ->
            var month = month
            month = month + 1
            Log.d(TAG, "onDateSet: dd/mm/yyy: $day/$month/$year")
            val date = "$day/$month/$year"
                addDueDateEditText.setText(date)}

        addTaskButton.setOnClickListener {
            val taskName=addTaskEditText.text.toString()
            val description=addDescriptionEditText.text.toString()
            val dueDate=addDueDateEditText.text.toString()

            toDoViewModel.addItem(taskName,description,dueDate,check = false)

            findNavController().popBackStack()

        }

    }


}