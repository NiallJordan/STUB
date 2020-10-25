package com.stub.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.layout.BorderPane
import tornadofx.*

class Assignment(
    module:String?=null,
    title: String? = null,
    description: String? = null,
    weight: Int = 0,
    subLink: String?= null,
    subDate: String? = null){

    val moduleProperty = SimpleStringProperty(this,"module", module)
    var module by moduleProperty

    val titleProperty = SimpleStringProperty(this,"title", title)
    var title by titleProperty

    val descriptionProperty = SimpleStringProperty(this,"description",description )
    var description by descriptionProperty

    val weightProperty = SimpleIntegerProperty(weight)
    var weight by weightProperty

    val subLinkProperty = SimpleStringProperty(this,"subLink",subLink )
    var subLink by subLinkProperty

    val subDateProperty = SimpleStringProperty(this,"subDate",subDate )
    var subDate by subDateProperty
}
