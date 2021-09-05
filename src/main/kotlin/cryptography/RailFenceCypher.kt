package cryptography

interface RailFenceInterface {
    fun encrypt(str: String): String
    fun decrypt(str: String): String
}
/**
 * Rail fence uses a matrix with cycles.
 * Cycle is the most important part of this algorithm because it decides where to put the character inside the matrix
 * Column index goes on by incrementing itself until string size
 * Row index is tricky -- upto a certain point it goes on increasing and start decreasing after a certain point
 *
 * If key is of size 3, then the cycle will be 0, 1, 2, 1 thats why the cycleSize is key-1 where it says the depth of cycle is 2 and after that
 * It should start decrementing the row index
* */
class RailFenceCypher(val key: Int, val listSize: Int): RailFenceInterface {
    private val cycleSize = key-1
    private val ematrix = Array(key) { Array<String?>(listSize + (listSize/3)) { null } }
    private val dmatrix = Array(key) { Array<String?>(listSize + (listSize/3)) { null } }

    override fun encrypt(str: String): String {
        var i = 0
        while (i < str.length) {
            for (j in 0 until cycleSize * 2) {
                if (i>str.length-1) continue
                ematrix[getCycleIndex(j)][i] = str[i].toString()
                i++
            }
        }

        var _str = ""
        for (k in 0 until key) {
            ematrix[k].forEach {
                it?.let {
                    _str += it
                }
            }
        }
        return _str
    }

    override fun decrypt(str: String): String {
        var i = 0
        var strI = 0
        /*
        * Treat each row in matrix differently
        * First fill first row with j=0
        * Then second with j=1 and so on..
        * */
        for (k in 0 until key) {
            while (i < str.length) {
                for (j in 0 until cycleSize * 2) {
                    if (i>str.length-1) continue
                    if (getCycleIndex(j) == k) {
                        dmatrix[getCycleIndex(j)][i] = str[strI].toString()
                        strI++
                    }
                    i++
                }
            }
            i=0
        }

        var _str = ""
        i = 0
        /**
         * Make the string by cycle indexes..
         * for key=3 it is 0,1,2,1,0,1,2,1,0 and so on..
        * */
        while (i < str.length) {
            for (j in 0 until cycleSize * 2) {
                ematrix[getCycleIndex(j)][i]?.let {
                    _str += it
                }
                i++
            }
        }
        return _str
    }

    private fun getCycleIndex(index: Int): Int {
        // 2 is static, this has to be rolled back by a nice strategy for different key sizes other then 3
        return if (index > cycleSize) index-2 else index
    }
}

fun main() {
    // only works with key of size 3 atm.
    val str = """
        A message is a discrete unit of communication intended by the source for consumption by some recipient or group of recipients. A message may be delivered by various means, including courier, telegraphy, carrier pigeon and electronic bus. A message can be the content of a broadcast.
    """.trimIndent()
    RailFenceCypher(3, str.length).apply {
        encrypt(str).also(::println).also {
            decrypt(it).also(::println)
        }
    }
}