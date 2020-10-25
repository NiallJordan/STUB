package com.stub.view.crud

import com.stub.model.AssignModel
import com.stub.model.Assignment
import javafx.beans.property.Property
import javafx.scene.layout.BorderPane
import tornadofx.*
import java.time.LocalDate

class AssignmentEditor:View("Assignment Editor"){
    val model : AssignModel by inject()
    override val root = form {
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
            button("Update") {
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

    private fun saveModel() {
        // Flush changes from the text fields into the model
        model.commit()
        // The edited person is contained in the model
        //Persistence to go here
        println("Saving ${model.item.title} / ${model.item.weight}")
    }
}