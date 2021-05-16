package algorithms.sets

/**
 * Permutation without repetition
 *
 * Permutation is ordering of items in a set, where ordering does matter.
 * s={1,2,3} -> formula to find permutations without repetition is n!/(n-r)!
 * with repetitions its n!
 *
 * s={1,2,3} -> 3!/(3-3)! = 6, where r=sample size, we want 3 items in each subset
 * 1,2,3
 * 1,3,2
 * 2,1,3
 * 2,3,1
 * 3,2,1
 * 3,1,2
 *
 *
 * How can we produce this via machine, we follow a binary tree like structure
 *
 *                        ABC
 *                   /    |     \
 *                /(0,0)  |(0,1)  \(0,2)
 *              ABC      BAC       CAB
 *          0,1/ 1,2\
 *          ABC     ACB
 *
 * And so on..
 * */
class Permutation {

    fun permute(s: String) {
        permute(s, 0, s.length - 1)
    }
    /*
     * We traverse each subtree to the end
     * starting with 0,0
     *               0,1
     *               1,2
     *
     * l increases with each permutation until it becomes equal to the length of string.
     * whats interesting is i starts with whatever l is,
     *
     * when l goes to the depth of left subtree, i is incremented to start with the right subtree until l reaches the depth.
     * and so on.
     *
     * when it has exhausted both subtrees, it backtracks to the root and start with the next tree.
     *
     * e.g
     * CS1: str=ABC, l=0, r=2, i=0
     * CS2: str=ABC, l=1, r=2, i=1
     * CS3: str=ABC, l=2, r=2 --> print
     *
     * goto CS2:
     * CS4: str=ABC, l=1, r=2, i=2
     * CS5: str=ACB, l=2, r=2 --> print
     *
     * goto CS4:
     * goto CS1:
     *
     * CS6: str=ABC, l=0, r=2, i=1
     * CS7: str=BAC, l=1, r=2, i=1
     * CS8: str=BAC, l=2, r=2 --> print
     *
     * goto CS7:
     * CS8: str=BCA, l=1, r=2, i=2
     * CS9: str=BCA, l=2, r=2 --> print
     *
     * goto CS8:
     * goto CS7:
     * goto CS6:
     * goto CS1:
     *
     * start with C tree.
    * */
    private fun permute(str: String, l: Int, r: Int) {
        if (l == r) {
            println(str)
        } else {
            for (i in l..r) {
                permute(swap(str, l, i), l + 1, r)
            }
        }
    }

    private fun swap(a: String, i: Int, j: Int): String {
        val str = a.toCharArray()
        val temp = str[i]
        str[i] = str[j]
        str[j] = temp
        return String(str)
    }
}

fun main() {
    Permutation().permute("ABC")
}