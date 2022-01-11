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
import java.text.SimpleDateFormat
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
        val addDueDateEditText:EditText=view.findViewById(R.id.add_Date_edittexts)
        val addTaskButton: Button =view.findViewById(R.id.add_item_button)


        addDueDateEditText.setOnClickListener() {
            val calender=Calendar.getInstance()
            val year:Int=calender.get(Calendar.YEAR)
            val month:Int=calender.get(Calendar.MONTH)
            var day:Int=calender.get(Calendar.DAY_OF_MONTH)
            val dailoge:DatePickerDialog= DatePickerDialog(requireContext(),R.style.abirStyle,
            mDateSet,year,month,day)
            dailoge.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dailoge.show()

                }

        mDateSet= OnDateSetListener { datePicker, year, month, day ->
            var months = month
             months = months + 1
            var days = day
            //Log.d(TAG, "onDateSet:yyy MMMM dd: $year $month $day")
            if(month<10) months = "0$months".toInt()
            if(day<10) days="0$days".toInt()
            val date = "$year/$months/$days"
                addDueDateEditText.setText(date)
            Log.d("day","$date")
        }



        addTaskButton.setOnClickListener {
            val taskName=addTaskEditText.text.toString()
            val description=addDescriptionEditText.text.toString()
            val dueDate=addDueDateEditText.text.toString()
            if (taskName.isNotEmpty() && dueDate.isNotEmpty()){
                toDoViewModel.addItem(taskName,description,dueDate,check = false)

                findNavController().popBackStack()
            }else{
                Toast.makeText(requireContext(), "task and date fields should be filled", Toast.LENGTH_SHORT).show()
            }




        }

    }


}