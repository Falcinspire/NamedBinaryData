package com.falcinspire.nbd

/**
 * @author Falcinspire
 * @version Jun/15/2017 (6:55 PM)
 */

class Compound(size : Int) : MutableMap<String, Any> {

    val internal_map = HashMap<String, Any>(size)

    override val entries = internal_map.entries
    override val keys = internal_map.keys
    override val values = internal_map.values
    override val size = internal_map.size

    override fun clear() = internal_map.clear()
    override fun put(key: String, value: Any) = internal_map.put(key, value)
    override fun putAll(from: Map<out String, Any>) = internal_map.putAll(from)
    override fun remove(key: String) = internal_map.remove(key)

    override fun containsKey(key: String) = internal_map.containsKey(key)
    override fun containsValue(value: Any) = internal_map.containsValue(value)
    override fun get(key: String) = internal_map.get(key)
    override fun isEmpty() = internal_map.isEmpty()

}

fun compoundOf(vararg tags : Pair<String, Any>) : Compound {
    val c = Compound(tags.size)
    c.internal_map.putAll(tags)
    return c
}