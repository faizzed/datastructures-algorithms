package datastructures.hashmap

import kotlin.collections.HashMap

/**
* Hash map implementation in java standard library
* */
fun main() {

    val hashMap = HashMap<String, String>(10)

    hashMap.put("key-1", "value-1")
    hashMap.put("key-1", "value-2")
    hashMap.put("key-1", "value-3")
    hashMap.put("key-2", "value-2")
    hashMap.put("key-3", "value-3")

    hashMap.also(::println)
}