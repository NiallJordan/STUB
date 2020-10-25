package com.stub.view.crud

import com.stub.controller.AssignmentController
import com.stub.model.AssignModel
import com.stub.model.Assignment
import tornadofx.*

class AssignmentList : View("My View") {
    val model : AssignModel by inject()
    val assignmentController: AssignmentController by inject()

    override val root = tableview<Assignment> {
        items = assignmentController.assignments
        title = "Assignment"
        column("Module", Assignment::moduleProperty)
        column("Title", Assignment::titleProperty)
        column("Description", Assignment::descriptionProperty)
        column("Weight", Assignment::weightProperty)
        column("Submission Link", Assignment::subLinkProperty)
        column("Submission Date", Assignment::subDateProperty)
        bindSelected(model)
        onUserSelect {
            workspace.dock<AssignmentEditor>()
        }
        smartResize()
    }
}

