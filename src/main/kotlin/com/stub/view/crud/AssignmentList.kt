package com.stub.view.crud

import com.stub.controller.AssignmentController
import com.stub.model.AssignModel
import com.stub.model.Assignment
import com.stub.util.AssignmentDao
import com.sun.jndi.toolkit.url.Uri
import javafx.geometry.Pos
import tornadofx.*
import java.awt.Desktop
import java.net.URI
import javax.naming.Context

class AssignmentList : View("STUB - Student Board") {
    val model: AssignModel by inject()
    val assignmentController: AssignmentController by inject()

    //Injecting binding model properties to the View properties
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
                            validator{
                                if (it.isNullOrBlank()) error("Module cannot be empty") else  null
                            }
                        }
                    }
                    field("Assignment Title") {
                        textfield(ttle) {
                            this.required()
                            validator{
                                if (it.isNullOrBlank()) error("Title cannot be empty") else  null
                            }
                        }
                    }
                    field("Description") {
                        textarea(desc) {
                            this.required()
                            validator{
                                if (it.isNullOrBlank()) error("Description cannot be empty") else  null
                            }

                        }
                    }

                    field("Percentage Weighting (%)") {
                        textfield(weight) {
                            this.required()
                            validator{
                                if (it.isNullOrBlank()) error("Weight cannot be empty") else  null
                            }
                        }
                    }

                    field("Submission Link") {
                        textfield(subLink) {
                            this.required()
                            validator{
                                if (it.isNullOrBlank()) error("Submission Link cannot be empty") else  null
                            }
                        }
                    }
                    field("Date Due") {
                        textfield(subDate) {
                            this.required()
                            validator{
                                if (it.isNullOrBlank()) error("Submission Date cannot be empty") else  null
                            }
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
            hbox {
                alignment= Pos.CENTER
                label("Search ")
                textfield("Enter Title"){
                    alignment= Pos.CENTER_RIGHT
                    action{
//                        dao.findAssignment()
                    }
                }
            }
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

                onDoubleClick {
                    val uri = URI(model.subLink.value)
                    openLink(uri)
                }
            }
        }
    }

    /**
     * Open Link function
     */
    private fun openLink(uri: URI){
        val desktop = if (Desktop.isDesktopSupported()) Desktop.getDesktop()else null
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            desktop.browse(uri)
        }
    }


    /**
     * Overriding onCreate function/button, creating a new assignment object and
     * passing that as a parameter for the assignment Controller create method.
     */
    override fun onCreate() {
        val assignment = Assignment(model.module.value,model.title.value,model.description.value,model.weight.value,model.subLink.value,model.subDate.value)
        assignmentController.createNewAssignment(assignment)
        //workspace.undock<AssignmentCreator>
    }

    /**
     * Overriding save function, calling the updateAssignment function from controller
     * and flushing all new changes to the table.
     */
    override fun onSave() {
        // Flush changes from the text fields into the model
        model.commit{
            //Persistence to go here
            assignmentController.updateAssignemnt()
        }
    }

    /**
     * Overriding onDelete function. Creating a new assignment object using the model (fields)
     * then calling the controller funciton delete to delete from table, db and pie
     * chart.
     */
    override fun onDelete() {
        val assignment = Assignment(model.module.value,model.title.value,model.description.value,model.weight.value,model.subLink.value,model.subDate.value)
        assignmentController.deleteAssignment(assignment)
    }

    //Rollback any changes to the model
    override fun onRefresh() {
        model.rollback()
    }
}


