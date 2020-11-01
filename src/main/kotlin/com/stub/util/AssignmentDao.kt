package com.stub.util

import com.stub.model.AssignModel
import com.stub.model.Assignment
import com.stub.view.crud.AssignmentList
import tornadofx.Controller

class AssignmentDao : Controller() {

    private val conn = Database().conn
    val assignmentList: AssignmentList by inject()


    fun addAssignment(assignment: Assignment){
        val ps = conn.prepareStatement("INSERT INTO stub(module,title,description,weight,subLink,subDate) VALUES (?,?,?,?,?,?)")
        ps.setString(1,assignment.module)
        ps.setString(2,assignment.title)
        ps.setString(3,assignment.description)
        ps.setInt(4,assignment.weight)
        ps.setString(5,assignment.subLink)
        ps.setString(6,assignment.subDate)
        val count = ps.executeUpdate()
        ps.close()
        return println("$count row inserted")
    }

    fun readAssignments() : List<Assignment> {
        val rs = conn.createStatement().executeQuery("SELECT * FROM stub WHERE module IS NOT NULL")
        val assignmentList = ArrayList<Assignment>()
        while (rs.next()) {
            val mod = rs.getString("module")
            val title = rs.getString("title")
            val desc = rs.getString("description")
            val weight = rs.getInt("weight")
            val subLink = rs.getString("subLink")
            val date = rs.getString("subDate")
            assignmentList += Assignment(mod, title, desc, weight, subLink, date)
        }
        rs.close()
        return assignmentList
    }

    fun updateAssignment(){
        val ps = conn.prepareStatement("UPDATE stub SET module = ? , title = ? , description = ? , weight = ? , subLink = ? , subDate = ? WHERE title =? ")
        ps.setString(1,assignmentList.module.value)
        ps.setString(2,assignmentList.ttle.value)
        ps.setString(3,assignmentList.desc.value)
        ps.setInt(4, assignmentList.weight.value)
        ps.setString(5,assignmentList.subLink.value)
        ps.setString(6,assignmentList.subDate.value)
        ps.setString(7, assignmentList.ttle.value )
        val count = ps.executeUpdate()
        ps.close()
        return println("$count row updated")
    }

    fun deleteAssignment(title: String){
        val ps = conn.prepareStatement("DELETE FROM stub WHERE title = ?")
        ps.setString(1,title)
        val count = ps.executeUpdate()
        ps.close()
        return println("$count row deleted")
    }
}