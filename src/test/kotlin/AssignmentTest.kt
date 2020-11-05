import com.stub.controller.AssignmentController
import com.stub.model.Assignment
import com.stub.util.AssignmentDao
import org.junit.AfterClass
import java.lang.Exception
import java.util.*
import javax.security.sasl.AuthenticationException
import kotlin.test.*

class AssignmentTest {
    private val assignmentController = AssignmentController()
    private val dao = AssignmentDao()

    private val testAssignment: Assignment = Assignment("Mob App", "Assignment 2", "This is a test assignment",100,"somewhere on moodle","30th December 2020")
    private val emptyAssignment: Assignment = Assignment(null ,null,null,0,null,null);
    private val emptyModuleAssignment: Assignment = Assignment(null, "Assignment 2", "This is a test assignment",100,"somewhere on moodle","30th December 2020")
    private val updatedAssignment: Assignment = Assignment("Mob App", "Assignment 1", "This is a test assignment",100,"somewhere on moodle","30th December 2020")

    @Test
    fun testNewAssignmentCreation(){
        assignmentController.createNewAssignment(testAssignment)
        val retrievedAssignment = assignmentController.getAssignment(testAssignment)
        assertNotNull(retrievedAssignment)
    }

    @Test
    fun testFailedAdd(){
        assertFailsWith<Exception> {
            assignmentController.createNewAssignment(emptyAssignment)
        }
    }

    @Test
    fun testAddWithEmptyModule(){
        assertFailsWith<Exception> {
            assignmentController.createNewAssignment(emptyModuleAssignment)
        }
    }

//    @Test
//    fun testUpdate(){
//        dao.updateAssignment()
//    }

    //Delete Created Assignment after tests ran
    @AfterTest
    fun testDeleteAssignment() {
        assertNotNull(assignmentController.deleteAssignment(testAssignment), "Assignment Deleted",)
    }
}