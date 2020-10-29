package com.stub.view.crud//package com.stub.view.crud

import com.stub.controller.AssignmentController
import com.stub.model.AssignModel
import com.stub.model.Assignment
import tornadofx.*


class AssignmentCreator: View("Assignment Creator"){
    val model : AssignModel by inject()
    val assignmentController : AssignmentController by inject()

    override val headingProperty = model.title

    override val root = form {
        fieldset {
            field("Module Name") {
                textfield(model.module){
                    this.required()
//                    validator {
//                        when {
//                            it?.isEmpty()!! -> error("Module cannot be empty")
//                            it!!.length >65 -> error("Too Long")
//                            else -> null
//                        }
//                    }
                }
            }
            field("Assignment Title") {
                textfield(model.title){
                    this.required()
//                    validator {
//                        when {
//                            it?.isEmpty()!! -> error("Title cannot be empty")
//                            it!!.length >65 -> error("Too Long")
//                            else -> null
//                        }
//                    }
                }
            }
            field("Description") {
                textarea(model.description){
                    this.required()
//                    validator {
//                        when {
//                            it?.isEmpty()!! -> error("Description cannot be empty")
//                            it!!.length >500 -> error("Description can not be longer than 500 characters")
//                            else -> null
//                        }
//                    }
                }
            }

            field("Percentage Weighting (%)") {
                textfield(model.weight){
                    this.required()
                    validator {
                        when(it) {
                            null -> error("Weighting cannot be empty")
                            else -> null
                        }
                    }
                }
            }

            field("Submission Link") {
                textfield(model.subLink){
                    this.required()
//                    validator {
//                        when {
//                            it?.isEmpty()!! -> error("Submission Link cannot be empty")
//                            it!!.length >65 -> error("Too Long")
//                            else -> null
//                        }
//                    }
                }
            }
            field("Date Due") {
                textfield(model.subDate){
                    this.required()
//                    validator {
//                        when {
//                            it?.isEmpty()!! -> error("Submission Date cannot be empty")
//                            it!!.length >65 -> error("Too Long")
//                            else -> null
//                        }
//                    }
                }
            }
        }
    }

    override fun onCreate() {
        val assignment = Assignment(model.module.value,model.title.value,model.description.value,model.weight.value,model.subLink.value,model.subDate.value)
        assignmentController.createNewAssignment(assignment)
        ///workspace.undock<AssignmentCreator>
        workspace.dock<MyView>()
    }
}