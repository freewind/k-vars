package github.freewind.kotlin.kvweb.framework

import github.freewind.kotlin.kvweb.kvars.Var
import github.freewind.kotlin.kvweb.kvars.Vars
import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.INPUT
import kotlinx.html.id
import kotlinx.html.js.onKeyUpFunction
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import kotlin.browser.document

fun INPUT.bindValue(data: Var<String>) {
    this.id = nextId()
    fun self() = document.getElementById(this.id) as HTMLInputElement?
    data.addListener { newValue ->
        self()?.run {
            this.value = newValue
        } ?: run {
            this.value = newValue
        }
    }
    this.onKeyUpFunction = { _ -> data.set(self().value) }
}

fun HtmlBlockTag.bindContent(data: Var<String>) {
    this.id = nextId()
    fun self() = document.getElementById(this.id) as HTMLElement?
    data.addListener { newValue ->
        self()?.run {
            this.innerHTML = data.get()
        } ?: run {
            text(newValue)
        }
    }
}

fun <T> HtmlBlockTag.bindChildren(items: Vars<T>, fn: (T) -> Unit) {
    this.id = nextId()
    fun self() = document.getElementById(this.id) as HTMLElement?
    items.addListener { item ->
        self()?.run {
            fn(item)
        } ?: run {
            fn(item)
        }
    }
}


private var id = 0
private fun nextId(): String {
    return "var-$id".also {
        id += 1
    }
}