package com.stub.view

import com.stub.view.crud.AddDataFragment
import com.stub.view.crud.ReadFragment
import tornadofx.*

class MyView : View("My View") {
    override val root = pane {
         tabpane{
            tab<AddDataFragment>()
            tab<ReadFragment>()
        }
    }
}
