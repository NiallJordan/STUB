package com.stub.view

import javafx.scene.control.TabPane
import tornadofx.*

class STUBWorkspace : Workspace("STUB-Student Tracker App", NavigationMode.Tabs) {

    init {

        tabContainer.tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
    }

}
