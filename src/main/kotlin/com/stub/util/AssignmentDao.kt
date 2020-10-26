package com.stub.util

import com.stub.model.Assignment

class AssignmentDao {

    fun addAssignment(assignment: Assignment){
        val connection = Database().conn
        val ps = connection.prepareStatement("INSERT INTO STUB(module,title,description,weight,subLink,subDate) VALUES (?,?,?,?,?,?)")
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
        val conn = Database().conn
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
}