package com.stub.view.crud

import com.stub.controller.AssignmentController
import com.stub.model.AssignModel
import com.stub.model.Assignment
import com.stub.view.MyView
import tornadofx.*


class AssignmentCreator: View("Assignment Creator"){
    val assignment : AssignModel by inject()
    val assignmentController : AssignmentController by inject()

    override val headingProperty = assignment.title

    override val root = form {
        fieldset {
            field("Module Name") {
                textfield(assignment.module)
            }
            field("Assignment Title") {
                textfield(assignment.title)
            }
            field("Description") {
                textarea(assignment.description)
            }

            field("Percentage Weighting (%)") {
                textfield(assignment.weight)
            }

            field("Submission Link") {
                textfield(assignment.subLink)
            }
            field("Date Due") {
                textfield(assignment.subDate)
            }
        }
    }


    override fun onCreate() {
        // Flush changes from the text fields into the model
        val assignment = Assignment(assignment.module.value,assignment.title.value,assignment.description.value,assignment.weight.value,assignment.subLink.value,assignment.subDate.value)
        assignmentController.createNewAssignment(assignment)
        workspace.dock<MyView>()
    }
}