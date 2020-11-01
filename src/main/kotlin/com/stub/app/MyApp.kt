package com.stub.app

import com.stub.controller.AssignmentController
import com.stub.model.AssignModel
import com.stub.view.STUBWorkspace
import com.stub.view.crud.AssignmentList
import javafx.stage.Stage
import tornadofx.App
import tornadofx.UIComponent

class MyApp: App(STUBWorkspace::class, Styles::class){
    val model : AssignModel by inject()

    override fun onBeforeShow(view: UIComponent) {
        workspace.dock<AssignmentList>()
    }

    override fun start(stage: Stage){
        with(stage){
            width = 1450.0
            height = 1000.0
        }
        super.start(stage)
    }

}