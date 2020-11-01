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
import tornadofx.observable
import java.sql.Connection

class AssignmentController: Controller() {
    val assignments = SortedFilteredList(items = getAllAssignments().asObservable())
    var pieData = FXCollections.observableArrayList<PieChart.Data>()
    private val logger = KotlinLogging.logger {}
    val model : AssignModel by inject()
    val dao = AssignmentDao()

    init{
        logger.info{"Launching STUB"}

        assignments.forEach{
            pieData.add(PieChart.Data(it.module, it.weight.toDouble()))
        }
    }

    fun createNewAssignment(assignment: Assignment){
        dao.addAssignment(assignment)
        assignments.add(assignment)
        pieData.add(PieChart.Data(assignment.module,assignment.weight.toDouble()))
    }

    fun getAllAssignments(): List<Assignment> = AssignmentDao().readAssignments()

//    fun updateAssignment(oldAssignment:Assignment, newModule:String,newTitle:String,newDescription:String,newWeight:Int,newSubLink:String,newSubDate:String) {
//        //val newAssignment = Assignment(newModule,newTitle,newDescription,newWeight,newSubLink,newSubDate)
//        dao.updateAssignment()
////        with(assignments){
////            remove(oldAssignment)
////            add(newAssignment)
////        }
//    }

//    fun updatePie(model: AssignModel){
//        var id = model.module.toString()
//        var currentIndex : Int
//        assignments.forEachIndexed {  index, assignment ->
//            if(id == assignment.module){
//                currentIndex = index
//                pieData[currentIndex].name = assignment.title
//                pieData[currentIndex].pieValue = assignment.weight.toDouble()
//            }else{
//            }
//        }
//    }
    fun deleteAssignment(assignment: Assignment){
        dao.deleteAssignment(assignment.title)
        deleteFromTable(model)
        deleteFromPiChart(model)
    }

    private fun deleteFromTable(model:AssignModel){
        var id = 0
        assignments.forEachIndexed { index, assignment ->
            if(assignment.title == model.title.value && index != -1){
                id = index
            }
        }
        assignments.removeAt(id)
    }

    private fun deleteFromPiChart(model: AssignModel){
        var id = 0
        pieData.forEachIndexed{index, data ->
            if(data.name == model.module.value && index != -1){
                id = index
            }
        }
        pieData.removeAt(id)
    }
}