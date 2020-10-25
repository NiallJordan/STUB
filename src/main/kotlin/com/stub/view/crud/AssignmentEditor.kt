package com.stub.view.crud

import com.stub.model.Assignment
import javafx.scene.layout.BorderPane
import tornadofx.*
import java.time.LocalDate


class AssignModel(assignment: Assignment) : ItemViewModel<Assignment>(assignment) {
    val module = bind(Assignment::moduleProperty)
    val title = bind(Assignment::titleProperty)
    val description = bind(Assignment::descriptionProperty)
    val weight = bind(Assignment::weightProperty)
    val subLink = bind(Assignment::subLinkProperty)
    val subDate = bind(Assignment::subDateProperty)
}

class AssignmentEditor:View("Assignment Editor"){
    override val root = BorderPane()
    val assignments = listOf(Assignment("Mobile App","Kotlin","Assignment 1 for mobile app",30,"https://moodle.wit.ie/mod/assign/view.php?id=3318657","2020-11-06")).asObservable()
    val model = AssignModel(Assignment())

    init{
        with(root){
            center{
                tableview(assignments) {
                    column("Module", Assignment::moduleProperty)
                    column("Title", Assignment::titleProperty)
                    column("Description", Assignment::descriptionProperty)
                    column("Weight", Assignment::weightProperty)
                    column("Submission Link", Assignment::subLinkProperty)
                    column("Submission Date", Assignment::subDateProperty)

                    model.rebindOnChange(this){
                        selectedAssignment -> item = selectedAssignment ?: Assignment()
                    }
                }
            }

            right{
                form {
                    fieldset {
                        field("Module Name") {
                            textfield(model.module)
                        }
                        field("Assignment Title") {
                            textfield(model.title)
                        }
                        field("Description") {
                            textarea(model.description)
                        }

                        field("Percentage Weighting (%)") {
                            textfield(model.weight)
                        }

                        field("Submission Link") {
                            textfield(model.subLink)
                        }
                        field("Date Due") {
                            textfield(model.subDate)
                        }
                        button("Save") {
                            enableWhen(model.dirty)
                            action {
                                saveModel()
                            }
                        }
                        button("Reset").action {
                            model.rollback()
                        }
                    }
                }
            }
        }
    }

    private fun saveModel() {
        // Flush changes from the text fields into the model
        model.commit()

        // The edited person is contained in the model
        val assignment = model.item

        //Persistence to go here
        println("Saving ${assignment.title} / ${assignment.weight}")
    }
}