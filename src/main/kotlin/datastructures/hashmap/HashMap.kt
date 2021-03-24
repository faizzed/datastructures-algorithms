package datastructures.hashmap

import datastructures.linkedlist.LinkedList

data class Entry<K, V>(val key: K, var value: V) {
    override fun toString(): String {
        return "{$key=$value}"
    }
}

/**
 * Hash Map:
 *
 * A hash map is a key value store.
 * Why the key?
 * Unlike arrays with integer indexes a key of many types can be useful in different situations.
 * And the data structure of map still is essentially an array but the key acts as a code (computed index)
 * that can find the value store or bucket in O(1) time.
 *
 * Hash code: many variants, but its (value % size)
 * A hash code can result in duplicated addresses therefore we need to pile the values when the address is the same.
 * Can result in a linear search again tho, if the pile gets bigger, this is bad again. The speed is lost.
* */
class HashMap<K, V>(size: Int) {
    /**
    * We have to keep an array, linked list beats the purpose of hash map
    * */
    private val arr = arrayOfNulls<LinkedList<Entry<K, V>>>(size)

    /**
     * Put an entry into the hash map.
     * Compute the hash code of the key and assign the entry to a bucket.
     * A bucket is a linked list.
     *
     * For duplicate keys, update the key value to the last value
    * */
    fun put(key: K, value: V) {
        // does this key already exist in arr
        if (arr[getHash(key)] != null) {
            val list = arr[getHash(key)]

            // check if element exists..
            // then replace
            val replaced = replaceIfKeyExist(list!!, key, value)
            // or just add it to the list
            if (!replaced) {
                list.add(Entry(key, value))
            }

            return
        }

        // or add another list to the array
        val linkedList = LinkedList<Entry<K, V>>()
        linkedList.add(Entry(key, value))
        arr[getHash(key)] = linkedList
    }

    private fun replaceIfKeyExist(list: LinkedList<Entry<K, V>>, key: K, value: V): Boolean {
        var temp = list.head
        while (temp != null) {
            if (temp.data.key == key) {
                temp.data.value = value

                return true
            }
            temp = temp.next
        }

        return false
    }

    private fun getHash(key: K): Int {
        return key.hashCode() % arr.size
    }

    /**
    * Get the value by hashCode
     * If the address has a linkedList, parse the linked list for the key
    * */
    fun get(key: K): Entry<K, V>? {
        val list = arr[getHash(key)]
        return list?.let {
            getByKey(list, key)
        }
    }

    private fun getByKey(list: LinkedList<Entry<K, V>>, key: K): Entry<K, V>? {
        var temp = list.head
        while (temp != null) {
            if (temp.data.key == key) {
                return temp.data
            }
            temp = temp.next
        }

        return null
    }

    override fun toString(): String {
        return arr.toList().toString()
    }
}

fun main() {
    val map = HashMap<String, String>(4).apply {
        put("ss", "dd")
        put("ss", "ddd")
        put("s", "dddd")
        put("s", "d")
        put("hello", "world")
        put("hello", "welt")
        put("key-1", "value-1")
        put("key-2", "value-1")
        put("key-3", "value-1")
        put("key-4", "value-1")
    }

    map.get("ss").also(::println)
    map.get("key-4")?.value.also(::println)
    map.get("s")?.value.also(::println)

    map.also(::println)

    val map2 = HashMap<Int, String>(4).apply {
        put(1, "dd")
        put(33, "ddd")
        put(9, "dddd")
        put(14, "d")
        put(1, "world")
        put(1, "welt")
        put(11, "value-1")
        put(12, "value-1")
        put(13, "value-1")
        put(14, "value-1")
    }

    map2.get(33).also(::println)
    map2.get(9)?.value.also(::println)
    map2.get(14)?.value.also(::println)
    map2.get(45)?.value.also(::println)

    map2.also(::println)
}