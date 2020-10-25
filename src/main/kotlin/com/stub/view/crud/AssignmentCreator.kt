package com.stub.view.crud

import com.stub.model.AssignModel
import tornadofx.*


class AssignmentCreator: View("Assignment Creator"){
    val assignment : AssignModel by inject()
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
        assignment.commit()
    }

    override fun onRefresh() {
        assignment.rollback()
    }
}