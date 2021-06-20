package algorithms.sets

class Substring {
    fun naive(s1: String, s2: String): Int {
        var itr = 0
        // start from the longest string
        for (i in 0..s1.length - s2.length) {
            itr++
            var j = 0
            // iterate times of smaller string
            while (j < s2.length) {
                itr++
                // if found an index check the next until the end reaches
                if (s1[i + j] != s2[j]) break
                j++
            }

            // found until the end then return
            if (j == s2.length) {
                println(itr)
                return i
            }
        }

        println(itr)
        // didnt find..
        return -1
    }

    fun strstr(s2: String, s1: String): Int {
        var itr = 0
        var counter = 0 //pointing s2
        var i = 0
        while (i < s1.length) {
            itr++
            if (counter == s2.length) break
            if (s2[counter] == s1[i]) {
                counter++
            } else {
                //Special case where character preceding the i'th character is duplicate
                if (counter > 0) {
                    i -= counter
                }
                counter = 0
            }
            i++
        }
        println(itr)
        return if (counter < s2.length) -1 else i - counter
    }
}

fun main() {
    Substring().naive("Hello", "lo").also(::println)
    Substring().strstr("lo", "Hello").also(::println)
}