package com.stub.model

import tornadofx.ItemViewModel

class StudentModel : ItemViewModel<Student>() {
    val id = bind(Student::id)
    val name = bind(Student::nameProperty)
    val username = bind(Student::usernameProperty)
    val password = bind(Student::passwordProperty)
}