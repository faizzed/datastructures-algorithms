package algorithms.strings

class KMP {
    /**
     * Naive approach to finding substring in a string
     * look for all characters in a substrings successively one by one in the main string
     * start with index 0 at substring
     * if we encounter matches increment m until we reach the end of m
     * if we reach the end -- yes
     * otherwise no
     *
     * There is a problem with this approach, if we throw away half encountered substrings we are throwing
     * away patterns that might make our substring.
     * example two doesnt work with this approach
    */
    fun naive(substring: String, mainString: String): Int {

        var substringCurrentPosition = 0

        for (i in mainString.indices) {
            if(mainString[i] == substring[substringCurrentPosition]) {
                substringCurrentPosition++
                // string matched...?
                if (substringCurrentPosition == substring.length) {
                    return i - substringCurrentPosition
                }
            } else {
                substringCurrentPosition = 0
            }
        }

        return -1
    }

    /**
     * Dont throw away half matched or decently mated patterns
     * Rather if there is a mismatch backtrack cursor i to i-substringCurrentPosition + 1
     * what that means is if there was a match and a mismatch halfway it means the starting point of the match can
     * not make the substring so eliminate just the head charater and start with preceding character if that will
     * make the substring
    */
    fun naivePlus(s: String, m: String): Int {

        // m = main string
        // s = substring
        var i = 0 // substring current position
        var j = 0 // main string current position

        while (j < m.length) {
            val ss = s[i]
            val mm = m[j]

            if(m[j] == s[i]) {
                i++
                // string matched...?
                if (i == s.length) {
                    return j - i
                }
            } else {
                j -= (i)
                i = 0
            }

            j++
        }


        return -1
    }

    fun kmpPiTable(str: String): MutableList<Int> {
        val table = mutableMapOf<String, Int>()

        for (i in str.indices) {
            if (table.containsKey(str[i].toString())) {
                val last = str[i-1].toString()
                val prev = table["${last}_${i-1}"] ?: 0
                table["${str[i]}_${i}"] = 1 + prev
            } else {
                table[str[i].toString()] = 0
            }
        }

        val piTable = mutableListOf<Int>()
        table.map {
            piTable.add(it.value)
        }
        return piTable
    }

    fun knuthMorrisPrattAlgorithm(pin: String, main: String): Int {
        val dict = kmpPiTable(pin)
        var pinIndex = 0
        var mainIndex = 0

        while (mainIndex < main.length) {

            if (main[mainIndex] == pin[pinIndex]) {
                pinIndex++
            } else {
                pinIndex = dict[pinIndex]
            }

            mainIndex++

            if (pinIndex == pin.length) {
                // completed search...
                return mainIndex - pinIndex
            }
        }

        return -1
    }

}

fun main() {
//    KMP().naivePlus("brown", "a lazy br fox jumps brow over a brown fence").also(::println)
//    KMP().naivePlus("ABCDABD", "ABC ABCDAB ABCDABCDABDE").also(::println)
    KMP().knuthMorrisPrattAlgorithm("dex", "dededex").also(::println)
    KMP().knuthMorrisPrattAlgorithm("ababd", "ababcabcabababdcdba").also(::println)
    KMP().knuthMorrisPrattAlgorithm("value pair", "How to allow php array to have duplicate keys? When I try to insert a key, value pair with already existing key it overwrites the value of corresponding previous key with the new value. Is there a way that I could maintain both duplicate keys having different values?").also(::println)
    KMP().knuthMorrisPrattAlgorithm("fox", "A brown sheep jumped over a dog").also(::println)
    KMP().kmpPiTable("abcdabeabf").also(::println)
    KMP().kmpPiTable("abababe").also(::println)
}