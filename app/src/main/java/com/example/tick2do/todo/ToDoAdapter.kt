package com.example.tick2do.todo

import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tick2do.R
import com.example.tick2do.database.model.TodoInfo
import com.example.tick2do.objects.PassedDueDate
import java.text.SimpleDateFormat
import java.util.*

class ToDoAdapter (val items:List<TodoInfo>,val viewModel:ToDoViewModel):RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(){
    class ToDoViewHolder(view: View):RecyclerView.ViewHolder(view){
        val taskName:TextView=view.findViewById(R.id.task_textview)
        val duedate:TextView=view.findViewById(R.id.due_date_textview)
        val isCompleted:CheckBox=view.findViewById(R.id.iscompleted_checkBox)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
            ))

    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val item=items[position]

        holder.taskName.text=item.taskName
//        holder.duedate.setTextColor(Color.parseColor(PassedDueDate.passedDueDate(item.dueDate)))
        holder.duedate.text="Due Date: ${item.dueDate}"


        holder.isCompleted.isChecked=item.isComplete

        if(item.isComplete){
            holder.taskName.paintFlags=Paint.STRIKE_THRU_TEXT_FLAG

        }
        var dueDate= Date()
        val format=SimpleDateFormat("yyyy/MM/dd")
        val date=format.parse(item.dueDate)
        if (dueDate>=date) {
            holder.duedate.setTextColor(Color.RED)
            holder.duedate.text="Due Date: ${item.dueDate} (passed)"
        }

        holder.itemView.setOnClickListener { it ->
            viewModel.selectedItemMutableLiveData.postValue(item)
            it.findNavController().navigate(R.id.action_realMainFragment_to_detailItemFragment)


        }
        holder.isCompleted.setOnClickListener{
            item.isComplete=holder.isCompleted.isChecked
//
            if(holder.isCompleted.isChecked){
                Log.d("onBindViewHolder", "isChecked")
                holder.taskName.paintFlags=Paint.STRIKE_THRU_TEXT_FLAG
            }else{
                holder.taskName.setPaintFlags(0)
            }

//            item.taskName=holder.taskName.apply {
//                Paint.STRIKE_THRU_TEXT_FLAG
//            }.toString()

            viewModel.updateItems(item)
        }

    }

    override fun getItemCount(): Int {
       return items.size
    }

}
