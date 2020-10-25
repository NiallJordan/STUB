package com.stub.view

import com.stub.model.AssignModel
import com.stub.model.Assignment
import javafx.scene.Parent
import tornadofx.*

class AssignmentList : View("My View") {
    val assignments = listOf(Assignment("Mobile App","Kotlin","Assignment 1 for mobile app",30,"https://moodle.wit.ie/mod/assign/view.php?id=3318657","2020-11-06")).asObservable()
    val model : AssignModel by inject()

    override val root = tableview(assignments) {
        title = "Assignment"
        column("Module", Assignment::moduleProperty)
        column("Title", Assignment::titleProperty)
        column("Description", Assignment::descriptionProperty)
        column("Weight", Assignment::weightProperty)
        column("Submission Link", Assignment::subLinkProperty)
        column("Submission Date", Assignment::subDateProperty)
        bindSelected(model)
    }
}
