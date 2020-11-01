package com.stub.view

import com.stub.view.crud.AssignmentList
import javafx.collections.FXCollections
import javafx.scene.chart.PieChart
import javafx.scene.control.TabPane
import tornadofx.*

class STUBWorkspace : Workspace("STUB-Student Tracker App", NavigationMode.Tabs) {


    init {
        //add(MainMenu::class)
        add(RestProgressBar::class)
        tabContainer.tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE

    }

}
