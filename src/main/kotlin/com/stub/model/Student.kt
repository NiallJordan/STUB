package com.stub.model

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class Student (name : String? = null, username: String?= null, password: String? = null){

    val nameProperty = SimpleStringProperty(this, "name", name)
    var name by nameProperty

    val usernameProperty = SimpleStringProperty(this, "username", username)
    var username by usernameProperty

    val passwordProperty = SimpleStringProperty(this,"password", password)
    var password by passwordProperty

}