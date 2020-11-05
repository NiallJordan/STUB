 package com.stub.view.login

import com.stub.controller.LoginController
import com.stub.model.Student
import com.stub.model.StudentModel
import tornadofx.*

class Register : View("My View") {
    val loginController: LoginController by inject()
    val studentModel : StudentModel by inject()
    val id = studentModel.id
    val name = studentModel.name
    val username = studentModel.username
    val password = studentModel.password

    override val root = borderpane {

        top = label("Register")

        left = imageview{
            //stub logo
        }

        right= form {
            fieldset {
                field("Name") { textfield(name) }
                field("Username") { textfield(username) }
                field("Password") { textfield(password) }

                buttonbar {
                    button ("Register") {
                        runAsync{
                            val student = Student(studentModel.id.value,studentModel.name.value,studentModel.username.value,studentModel.password.value)
                            loginController.register(student)
                        }
                    }
                }

                field {
                    button ("Go to Login") {
                        action{
                            replaceWith<Login>()
                        }
                    }
                }
            }
        }
    }
}
