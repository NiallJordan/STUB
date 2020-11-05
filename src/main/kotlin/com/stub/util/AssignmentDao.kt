package com.stub.util

import com.stub.model.AssignModel
import com.stub.model.Assignment
import com.stub.view.crud.AssignmentList
import javafx.beans.property.SimpleStringProperty
import tornadofx.Controller
import java.sql.PreparedStatement
import java.sql.SQLException

class AssignmentDao : Controller() {

    private val conn = Database().conn
    val assignmentList: AssignmentList by inject()


    /**
     * Database query for adding an assignment to the database
     */
    fun addAssignment(assignment: Assignment){
        try{
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
       }catch (e : SQLException){
           e.printStackTrace()
       }
    }

    /**
     * Find an assignment based on the title of that assignment
     */
    fun findAssignment(title: String): Assignment?{
        return this.readAssignments()?.find{ assignment -> assignment.title == title  }
    }

    /**
     * Read all assingments from the database using a Select query and assign to
     * type List
     */
    fun readAssignments() : List<Assignment> {
        val assignmentList = ArrayList<Assignment>()

        try {
            val rs = conn.createStatement().executeQuery("SELECT * FROM stub")

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
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return assignmentList
    }


    /**
     * Update query for the database based on the title
     */
    fun updateAssignment(){
        try{
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
        }catch (e : SQLException){
            e.printStackTrace()
        }
    }

    /**
     * Delete query to remove an assignment from the database, returns a count of
     * the amount of rows deleted.
     */
    fun deleteAssignment(title: String){
        try {
            val ps = conn.prepareStatement("DELETE FROM stub WHERE title = ?")
            ps.setString(1, title)
            val count = ps.executeUpdate()
            ps.close()
            return println("$count row deleted")
        }catch (e: SQLException){
            e.printStackTrace()
        }
    }
}