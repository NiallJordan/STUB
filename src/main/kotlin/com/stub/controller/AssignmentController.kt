package com.stub.controller

import com.stub.model.AssignModel
import com.stub.model.Assignment
import com.stub.util.AssignmentDao
import javafx.collections.FXCollections
import javafx.scene.chart.PieChart
import mu.KotlinLogging
import tornadofx.Controller
import tornadofx.SortedFilteredList
import tornadofx.asObservable

class AssignmentController: Controller() {
    val assignments = SortedFilteredList(items = getAllAssignments().asObservable())
    //val  filteredAssignments = FilteredList
    var pieData = FXCollections.observableArrayList<PieChart.Data>()
    private val logger = KotlinLogging.logger {}
    val model : AssignModel by inject()
    val dao = AssignmentDao()


    /**Initialise logger and for each assignment in the database
     * and adds the title and weight data to the pie chart.
     */
    init{
        logger.info{"Launching STUB"}

        assignments.forEach{
            pieData.add(PieChart.Data(it.title, it.weight.toDouble()))
        }
    }

    /**
     * CreateNewAssingment function that takes an assignment as a paramter.
     * This calls the database add function, adds the assignment to the SortedList
     * , and also adds the new data observable array to the pie data for the chart.
     */
    fun createNewAssignment(assignment: Assignment){
        dao.addAssignment(assignment)
        assignments.add(assignment)
        pieData.add(PieChart.Data(assignment.title,assignment.weight.toDouble()))
    }

    //Get Assignment function that calls the database find a single assignment.
    fun getAssignment(assignment: Assignment){
        dao.findAssignment(assignment.title)
    }

    //Calls the update fuction and the update pie function.
    fun updateAssignemnt(){
        dao.updateAssignment()
        updatePie(model)
    }

    /**Calls the readAssignments function from the database access object and assigns
     * the values to a list.
    */
    fun getAllAssignments(): List<Assignment> = AssignmentDao().readAssignments()

    private fun updatePie(model: AssignModel){
        var id = 0
        var currentIndex : Int
        assignments.forEachIndexed {  index, assignment ->
            if(assignment.title == model.title.value && index != -1){
                currentIndex = index
                pieData[currentIndex].name = assignment.title
                pieData[currentIndex].pieValue = assignment.weight.toDouble()
            }else{
            }
        }
    }

    /**
     * Function that calls all methods to delete, including the database delete from
     * the Assignment DAO, delete from the tableview and delete from the Pie Chart.
     */
    fun deleteAssignment(assignment: Assignment){
        dao.deleteAssignment(assignment.title)
        deleteFromTable(model)
        deleteFromPiChart(model)
    }

    /**
     * Function that iterates over each object in the assignments list, compares
     * the title of the asssigment to that of the selected model from the table
     * and removes that item from the tableview
     */
    private fun deleteFromTable(model:AssignModel){
        var id = 0
        assignments.forEachIndexed { index, assignment ->
            if(assignment.title == model.title.value && index != -1){
                id = index
            }
        }
        assignments.removeAt(id)
    }


    /**
     * Dynamically deleting entry from pie chart if
     * the name in the pie data is equal to model.module
     */
    private fun deleteFromPiChart(model: AssignModel){
        var id = 0
        pieData.forEachIndexed{index, data ->
            if(data.name == model.title.value && index != -1){
                id = index
            }
        }
        pieData.removeAt(id)
    }
}