package com.example.tick2do.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tick2do.R
import com.example.tick2do.database.model.TodoInfo

class ToDoAdapter (val items:List<TodoInfo>,val viewModel:ToDoViewModel):RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(){
    class ToDoViewHolder(view: View):RecyclerView.ViewHolder(view){
        val taskName:TextView=view.findViewById(R.id.task_textview)
        val creationdate:TextView=view.findViewById(R.id.creation_date_textview)
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
        holder.creationdate.text=item.creationDate
        holder.isCompleted.isChecked=item.isComplete


        holder.itemView.setOnClickListener { it ->
            viewModel.selectedItemMutableLiveData.postValue(item)
            it.findNavController().navigate(R.id.action_realMainFragment_to_detailItemFragment)


        }
        holder.isCompleted.setOnClickListener{
            item.isComplete=holder.isCompleted.isChecked
            viewModel.updateItems(item)
        }

    }

    override fun getItemCount(): Int {
       return items.size
    }

}