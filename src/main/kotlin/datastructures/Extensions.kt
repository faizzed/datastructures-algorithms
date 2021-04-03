package datastructures

fun Int.toBinaryString(characters: Int = 8): String {
    return Integer.toBinaryString(this).padStart(characters, '0')
}