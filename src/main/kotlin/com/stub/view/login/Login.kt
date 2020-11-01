package com.stub.view.login

import com.stub.app.MyApp
import com.stub.view.STUBWorkspace
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class Login : View("My View") {


    override val root = borderpane {
        top = label ("Login to STUB") {
        }

        left = imageview{
            //stub logo
        }

        right= form {
            fieldset {
                field {
                    textfield("Username")
                }
                field {
                    textfield("Password") {  }
                }

                field {
                    button ("Submit") {
                        action{
//                            myusername = username.value
//                            mypassword = password.value

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
