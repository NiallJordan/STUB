package com.stub.util

import com.stub.model.AssignModel
import com.stub.model.Assignment

class AssignmentDao {

    private val conn = Database().conn


    fun addAssignment(assignment: Assignment){
        val ps = conn.prepareStatement("INSERT INTO STUB(module,title,description,weight,subLink,subDate) VALUES (?,?,?,?,?,?)")
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
        val rs = conn.createStatement().executeQuery("SELECT * FROM STUB WHERE module IS NOT NULL")
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

    fun updateAssignment(title:String,assignment: Assignment){
        val modParam = ""
        val titleParam=""
        val descParam = ""
        val weight = 0
        val subLink=""
        val subDate = ""
        var optionalParamIndex = 6
//        if(assignment.)

    }

    fun deleteAssignment(title: String){
        val ps = conn.prepareStatement("DELETE FROM stub WHERE title = ?")
        ps.setString(1,title)
        val count = ps.executeUpdate()
        ps.close()
        return println("$count row deleted")
    }
}