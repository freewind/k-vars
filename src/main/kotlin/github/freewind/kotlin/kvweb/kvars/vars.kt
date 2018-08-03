package github.freewind.kotlin.kvweb.kvars

typealias Listener<T> = (T) -> Unit

class Var<T>(initValue: T) {
    private var _value = initValue
    private val listeners = mutableListOf<Listener<T>>()

    fun addListener(listener: Listener<T>) {
        this.listeners.add(listener)
        listener(this._value)
    }

    fun get() = this._value

    fun set(value: T) {
        val changed = this._value != value
        this._value = value
        if (changed) {
            listeners.forEach { it(value) }
        }
    }

    fun <K> map(fn: (T) -> K): Var<K> {
        val k = Var(fn(get()))
        this.addListener { v ->
            k.set(fn(v))
        }
        return k
    }
}

class Vars<T>(initValue: List<T>) {
    private var _value = initValue

    private val listeners = mutableListOf<Listener<T>>()

    fun addListener(listener: Listener<T>) {
        this.listeners.add(listener)
        this._value.forEach { listener(it) }
    }

}


