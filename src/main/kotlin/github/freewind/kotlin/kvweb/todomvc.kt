package github.freewind.kotlin.kvweb

import github.freewind.kotlin.kvweb.framework.bindContent
import github.freewind.kotlin.kvweb.framework.bindChildren
import github.freewind.kotlin.kvweb.framework.bindValue
import github.freewind.kotlin.kvweb.kvars.Var
import github.freewind.kotlin.kvweb.kvars.Vars
import kotlinx.html.*
import kotlinx.html.dom.create
import org.w3c.dom.Node
import kotlin.browser.document

data class Item(val text: String, val complete: Boolean)

private val input = Var("")
private val items = Vars<Item>()

fun main(args: Array<String>) {
    document.body!!.append(createView())
}

fun createView(): Node = document.create.div {
    div {
        textInput { this.bindValue(input) }
    }
    div { this.bindContent(input) }
    ul {
        this.bindChildren(items) { item ->
            li {
                text(item.text)
            }
        }
    }
}




