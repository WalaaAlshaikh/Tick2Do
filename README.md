![Image of Tuwaiq](https://camo.githubusercontent.com/37ca472e2afb74974a0314d89af8f470422a79582bed0d188f9927777230195d/68747470733a2f2f6c61756e63682e73612f6173736574732f696d616765732f6c6f676f732f7475776169712d61636164656d792d6c6f676f2e737667)
# Capstone 1 
Twaiaq Academy First Project.
To-Do list Android Application
## Overview:
![Image of app logo](https://g.top4top.io/p_2132buy3s1.png)


This project represents an android application **Tick2Do**, which helps the user creates and organize their tasks.
## Technologies used:
This application was built using the following technologies:
### For Designing the logo of the app:
* Canva

* Adobe Photoshop
### For Designing the pages of the app"
* Figma 

* Adobe Photoshop
### For Programming the app:
* [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows) for improving code quality.
* Android Architecture Components:Room,LiveData,ViewModel and Data binding.
* Room DataBase.
* RecyclerViews & Adapters.
* 


## Wireframes and User stories:
- As a user,I want to display a list so that I can view my tasks.

- As a user, I would like to be able to create multiplte tasks so that I can organize my workload.

- As a user I want to add the description so that I can provide additional information to the task.

- As a user I want to add due date, so that I can keep track of when each of my tasks is the deadline.

- As a user I would like to be able to delete tasks so that I reduce the number of unwanted tasks and to get confirmation before eliminating them.

- As a user, I want to be able to edit my tasks so that i can change the details of a certain task (its name,description,due date)

- As a user ,I want to be provideed with a notification so that i can be informed when the deadline is approaching.

- As a user ,I want to split the completed tasks from the uncompleted once so that my task can be more organized and less distracted.
-------------------------------------------------------------------------
## Installation:
Follow the steps below to get started with the project's development environment:
* Install Android Studio from [Android Studio](https://developer.android.com/studio?gclid=Cj0KCQjw5oiMBhDtARIsAJi0qk2WOPjxp2Wij5sgO3bAK6Rp18zrs4Y0L5S6W89Fk7OClhAiVuNr1mgaAsT-EALw_wcB&gclsrc=aw.ds)
## Development Process and Problem-solving Strategy:
Firstly, i brainstormed some ideas related to the requirement of to do list app and then took a general idea of the design and the mechanisim of some popular apps from app store
Secondly, I designed a logo according to the purpose of the app and gave it name.
Thirdly, I designed the screens each of them suitable for a specific action using the Figma and Photoshop,after that i statred programming my app using the android studio: at first, I installed the required libraries, then I created the fragments 
##  Unsolved Problems which would be fixed in future iterations:
-The problem of view the list with more than 6 items.It would be potentially solved by **"DiffUtil"** in order to calculate the updates of the list in the RecyclerView Apapter.
-

## My favorite functions work:
* pop up function
it is useful when you want to give the user a warning or a confirmation of a certain task.
```kotlin
val aluilder = AlertDialog.Builder(requireContext())
            aluilder.setTitle("Delete Notification")
            aluilder.setMessage("This task will be deleted and it can not be undone \n Are you sure you want to do this")
            aluilder.setPositiveButton("Yes") { dialogInterface, which ->
                toDoViewModel.deleteItem(selectedItem)
                findNavController().popBackStack()
            }

            aluilder.setNegativeButton("No") { dialogInterface, which ->

            }
            val theDialog: AlertDialog = aluilder.create()
            theDialog.setCancelable(false)
            theDialog.show()
```            
* Date Picker as a dialog with customized style
when you put the date in a dialog it would be easeir to the user to pick the date in a separate page and then designed 
