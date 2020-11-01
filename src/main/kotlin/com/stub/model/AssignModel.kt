package com.stub.model

import javafx.beans.property.Property
import tornadofx.Commit
import tornadofx.ItemViewModel


class AssignModel: ItemViewModel<Assignment>() {

    val module = bind(Assignment::moduleProperty)
    val title = bind(Assignment::titleProperty)
    val description = bind(Assignment::descriptionProperty)
    val weight = bind(Assignment::weightProperty)
    val subLink = bind(Assignment::subLinkProperty)
    val subDate = bind(Assignment::subDateProperty)


    //On commit if there are changes, the changes will print tp the console
    override fun onCommit(commits: List<Commit>) {
        // The println will only be called if findChanged is not null
        commits.findChanged(module)?.let { println("Module changed from ${it.second} to ${it.first}")}
        commits.findChanged(title)?.let { println("Title changed from ${it.second} to ${it.first}")}
        commits.findChanged(description)?.let { println("Description changed from ${it.second} to ${it.first}")}
        commits.findChanged(weight)?.let { println("Weight changed from ${it.second} to ${it.first}")}
        commits.findChanged(subLink)?.let { println("SubLink changed from ${it.second} to ${it.first}")}
        commits.findChanged(subDate)?.let { println("SubDate changed from ${it.second} to ${it.first}")}
    }

    private fun <T> List<Commit>.findChanged(ref: Property<T>): Pair<T, T>? {
        val commit = find { it.property == ref && it.changed}
        return commit?.let { (it.newValue as T) to (it.oldValue as T) }
    }
}