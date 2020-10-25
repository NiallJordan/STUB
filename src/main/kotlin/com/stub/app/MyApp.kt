package com.stub.app

import com.stub.view.MyView
import com.stub.view.STUBWorkspace
import javafx.stage.Stage
import mu.KotlinLogging
import tornadofx.App
import tornadofx.UIComponent

class MyApp: App(STUBWorkspace::class, Styles::class){
    private val logger = KotlinLogging.logger {}

    override fun onBeforeShow(view: UIComponent) {
        workspace.dock<MyView>()
    }

    override fun start(stage: Stage){
        with(stage){
            width = 1450.0
            height = 600.0
        }
        super.start(stage)
        logger.info{"Launching STUB"}
    }

}