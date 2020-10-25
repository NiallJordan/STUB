package com.stub.view.crud

import com.stub.model.AssignModel
import tornadofx.*

class AssignmentEditor:View("Assignment Editor"){
    val assignment : AssignModel by inject()
    override val savable = assignment.dirty
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

    override fun onSave() {
        // Flush changes from the text fields into the model
        assignment.commit()
        //Persistence to go here
        println("Saving ${assignment.item.title}")
    }

    override fun onRefresh() {
        assignment.rollback()
    }
}