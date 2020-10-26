package com.stub.view

import com.stub.view.crud.AssignmentCreator
import com.stub.view.crud.AssignmentList
import tornadofx.*

class MyView : View("STUB") {
    override val root = tabpane {
            tab<AssignmentList>()
    }

    override fun onCreate(){
        workspace.dock<AssignmentCreator>()
    }

}




