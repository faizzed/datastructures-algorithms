package algorithms.strings

fun linearReverse(s: String): String {
    val charArray = s.toCharArray()
    var reversed = ""
    for (i in charArray.size - 1 downTo 0) {
        reversed += charArray[i]
    }
    return reversed
}

// going through half the array
fun binaryReverse(s: String): String {
    val mid = s.length/2
    val charArray = s.toCharArray()
    if (mid<0) {
        return ""
    }

    var farRight = s.length-1
    for (i in 0 until mid) {
        val tmp = charArray[i]
        charArray[i] = charArray[farRight]
        charArray[farRight] = tmp
        farRight--
    }

    return String(charArray)
}

fun main() {
    println(linearReverse("Hello world!"))
    println(binaryReverse("Hello world!"))
    println(binaryReverse("This is a string!"))
}