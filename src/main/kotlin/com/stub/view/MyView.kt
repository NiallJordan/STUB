package com.stub.view

import com.stub.view.crud.AssignmentEditor
import tornadofx.*

class MyView : View("STUB") {
    override val root = tabpane {
            tab<AssignmentEditor>()
    }
}




