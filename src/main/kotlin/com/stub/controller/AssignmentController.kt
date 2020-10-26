package com.stub.controller

import com.stub.model.AssignModel
import com.stub.model.Assignment
import com.stub.util.AssignmentDao
import javafx.collections.FXCollections
import mu.KotlinLogging
import tornadofx.Controller
import tornadofx.asObservable

class AssignmentController: Controller() {
    val assignments = FXCollections.observableArrayList<Assignment>(Assignment("Mobile App","Kotlin","Assignment 1 for mobile app",30,"https://moodle.wit.ie/mod/assign/view.php?id=3318657","2020-11-06"))
    private val logger = KotlinLogging.logger {}
    val model : AssignModel by inject()

    init{
        logger.info{"Launching STUB"}
    }

    fun createNewAssignment(assignment: Assignment){
        assignments.add(assignment)
        val dao = AssignmentDao()
        dao.addAssignment(assignment)
    }

    fun getAllAssignments(): List<Assignment> {

    }
}