package com.example.tick2do.todo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tick2do.R
import com.example.tick2do.database.model.TodoInfo
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class RealMainFragment : Fragment() {


    private val todoItemsList= mutableListOf<TodoInfo>()
    private val toDoViewModel:ToDoViewModel by activityViewModels()
    val channelId:String="Channel"
    val notificationId=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_real_main, container, false)


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val todoRecyclerView:RecyclerView=view.findViewById(R.id.todo_recyclerView)
        val addFloatingButton:FloatingActionButton=view.findViewById(R.id.add_floating_button)
        val toDoAdapter=ToDoAdapter(todoItemsList,toDoViewModel)
        val linearLayout: LinearLayout = view.findViewById(R.id.linearLayout4)

        todoRecyclerView.adapter=toDoAdapter

        toDoViewModel.doDoItems.observe(viewLifecycleOwner, Observer {
            it?.let { items ->
                todoItemsList.clear()
                todoItemsList.addAll(items)

                // for the visibility of the linearlayout
                linearLayout.setVisibility(View.INVISIBLE)
                if(todoItemsList.isNullOrEmpty()){
                    linearLayout.setVisibility(View.VISIBLE)
                }

                var afterReturnedList= deadlineTaskApproch(todoItemsList)

                toDoAdapter.notifyDataSetChanged()

            }
        })






        addFloatingButton.setOnClickListener {

            findNavController().navigate(R.id.action_realMainFragment_to_addItemFragment)

        }


    }
    fun createNotificationChannel(name:String,descriptionText:String,id:Int) {
        var builder = NotificationCompat.Builder(requireContext(), channelId)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle("Approached Date")
            .setContentText("There are tasks which their due date is approaching within 24 hours")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Much longer text that cannot fit one line..."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name ="Notification"
            val descriptionText ="channel des"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getActivity()?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            with(NotificationManagerCompat.from(requireContext())) {
                // notificationId is a unique int for each notification that you must define
                notify(notificationId, builder.build())

            }
        }
    }


}
///creating a function For adding tasks that are a day before due date
@RequiresApi(Build.VERSION_CODES.O)
fun deadlineTaskApproch(list:MutableList<TodoInfo>):MutableList<TodoInfo>{
    val firstDay=LocalDate.now()
    var listWhichReturned= mutableListOf<TodoInfo>()
    list.forEach{
        //////date formatting from string to date/////
        val duedateFormater=it.dueDate
        val formatter:DateTimeFormatter= DateTimeFormatter.ofPattern("yyyy/mm/dd")
        var secondDay=LocalDate.parse(duedateFormater,formatter)
        var daysBetween:Long=
            Duration.between(firstDay.atStartOfDay(), secondDay.atStartOfDay()).toDays()
        Log.d("daysbetween", daysBetween.toString())
        if (daysBetween.toString()=="1"){
            Log.d(" the list", "inside if")
            listWhichReturned.add(it)

        }

    }
    Log.d("The list", listWhichReturned.toString())
    /// return the final change///
    return listWhichReturned

}