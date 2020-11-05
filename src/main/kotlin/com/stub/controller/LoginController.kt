package com.stub.controller

import com.stub.model.Student
import com.stub.util.StudentDao
import tornadofx.Controller

class LoginController : Controller() {
    val dao = StudentDao()

    fun login(student: Student){
    }

    fun register(student: Student){
        dao.register(student)
    }
}