package com.stub.view.crud

import com.stub.controller.AssignmentController
import com.stub.model.AssignModel
import com.stub.model.Assignment
import com.stub.util.AssignmentDao
import javafx.collections.FXCollections
import javafx.geometry.Pos
import javafx.scene.chart.PieChart
import tornadofx.*
import java.awt.Desktop
import java.net.URI

class AssignmentList : View("STUB - Student Board") {
    val model: AssignModel by inject()
    val assignmentController: AssignmentController by inject()
    val module = model.module
    val ttle = model.title
    val desc = model.description
    val weight = model.weight
    val subLink = model.subLink
    val subDate = model.subDate
    val dao = AssignmentDao()


    override val root = borderpane {
        left = vbox {
            form {
                fieldset {
                    field("Module Name") {
                        textfield(module) {
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
                        textfield(ttle) {
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
                        textarea(desc) {
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
                        textfield(weight) {
                            this.required()
                            validator {
                                when (it) {
                                    null -> error("Weighting cannot be empty")
                                    else -> null
                                }
                            }
                        }
                    }

                    field("Submission Link") {
                        textfield(subLink) {
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
                        textfield(subDate) {
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
        }
        right = vbox {
            piechart {
                data = assignmentController.pieData
            }
        }

        bottom = vbox {
            tableview<Assignment> {
                items = assignmentController.assignments
                column("Module", Assignment::moduleProperty)
                column("Title", Assignment::titleProperty)
                column("Description", Assignment::descriptionProperty)
                column("Weight", Assignment::weightProperty)
                column("Submission Link", Assignment::subLinkProperty)
                column("Submission Date", Assignment::subDateProperty)
                bindSelected(model)
                smartResize()

//                onDoubleClick {
//                    //Desktop.getDesktop().browse(URI());
////                    openLink(subLin)
//                }
            }
        }
    }

//    private fun openLink(uri: URI){
//        val desktop = if (Desktop.isDesktopSupported()) Desktop.getDesktop()else null
//        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
//            desktop.browse(uri)
//        }
//    }

    override fun onCreate() {
        val assignment = Assignment(model.module.value,model.title.value,model.description.value,model.weight.value,model.subLink.value,model.subDate.value)
        assignmentController.createNewAssignment(assignment)
        //workspace.undock<AssignmentCreator>
    }

    override fun onSave() {
        // Flush changes from the text fields into the model
        model.commit()
        //val assignment = Assignment(model.module.value,model.title.value,model.description.value,model.weight.value,model.subLink.value,model.subDate.value)

        //Persistence to go here
        dao.updateAssignment()
        // assignmentController.updatePie(model)
        //assignmentController.updateAssignment()
    }

    override fun onDelete() {
        val assignment = Assignment(model.module.value,model.title.value,model.description.value,model.weight.value,model.subLink.value,model.subDate.value)
        assignmentController.deleteAssignment(assignment)
    }

//    override fun onRefresh() {
//        root.onRefresh()
//    }
}

