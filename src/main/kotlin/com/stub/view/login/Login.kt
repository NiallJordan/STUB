package com.stub.view.login

import com.stub.app.MyApp
import com.stub.controller.LoginController
import com.stub.model.Student
import com.stub.model.StudentModel
import com.stub.view.STUBWorkspace
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class Login : View("My View") {
    val loginController: LoginController by inject()
    val studentModel : StudentModel by inject()
    val id = studentModel.id
    val name = studentModel.name
    val username = studentModel.username
    val password = studentModel.password



    override val root = borderpane {
        top = label ("Login to STUB") {
        }

        left = imageview{
            //stub logo
        }

        right= form {
            fieldset {
                field("Username") { textfield(username) }
                field("Password") { textfield(password) }

                buttonbar {
                    button ("Login") {
                        runAsync{
                            val student = Student(studentModel.id.value,studentModel.name.value,studentModel.username.value,studentModel.password.value)
                            loginController.login(student)
                        }
                    }
                }

                field {
                    button ("Register") {
                        action{
                            replaceWith<Register>()
                        }
                    }
                }
            }
        }
    }
}
