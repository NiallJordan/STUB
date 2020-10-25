package com.stub.app

import com.stub.view.STUBWorkspace
import javafx.stage.Stage
import mu.KotlinLogging
import tornadofx.App

class MyApp: App(STUBWorkspace::class, Styles::class){
    private val logger = KotlinLogging.logger {}

    override fun start(stage: Stage){
        with(stage){
            width = 1200.0
            height = 600.0
        }
        super.start(stage)
        logger.info{"Launching STUB"}
    }

}