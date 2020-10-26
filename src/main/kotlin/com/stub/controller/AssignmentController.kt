package com.stub.controller

import com.stub.model.AssignModel
import com.stub.model.Assignment
import com.stub.util.AssignmentDao
import javafx.collections.FXCollections
import mu.KotlinLogging
import tornadofx.Controller
import tornadofx.SortedFilteredList
import tornadofx.asObservable
import tornadofx.observable

class AssignmentController: Controller() {
    val assignments = SortedFilteredList(items = getAllAssignments().asObservable())
    private val logger = KotlinLogging.logger {}
    val model : AssignModel by inject()

    init{
        logger.info{"Launching STUB"}
    }

    fun createNewAssignment(assignment: Assignment){
        val dao = AssignmentDao()
        dao.addAssignment(assignment)
        assignments += assignment
    }

    fun getAllAssignments(): List<Assignment> = AssignmentDao().readAssignments()
}