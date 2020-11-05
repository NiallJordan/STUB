package com.stub.util

import com.stub.model.Assignment
import com.stub.model.Student
import com.stub.view.crud.AssignmentList
import com.stub.view.login.Login
import tornadofx.Controller

class StudentDao : Controller(){

    private val conn = Database().conn
    val login: Login by inject()

    fun register(student:Student){
        val ps = conn.prepareStatement("INSERT INTO account(id,name,username,password) VALUES (?,?,?,?)")
        ps.setInt(1,student.id)
        ps.setString(2,student.name)
        ps.setString(3,student.username)
        ps.setString(4,student.password)
        val count = ps.executeUpdate()
        ps.close()
        return println("$count user created")
    }

    fun login(student:Student){
        val ps = conn.prepareStatement("SELECT * FROM account WHERE username = ?")
        ps.setString(1,student.username)
        val count = ps.executeUpdate()
        ps.close()
        return println("$count user created")
    }

//    fun getUser(username: String): Student?{
//        return this.getAllStudents()?.find{ student -> student.username == username  }
//    }
//
//    fun getAllStudents() : List<Student>? {
//        val rs = conn.createStatement().executeQuery("SELECT * FROM account")
//        val studentList = ArrayList<Student>()
//        while (rs.next()) {
//            val id = rs.getInt("id")
//            val name = rs.getString("name")
//            val username = rs.getString("username")
//            val password = rs.getString("password")
//            studentList += Student(id,name,username,password)
//        }
//        rs.close()
//
//    }

}