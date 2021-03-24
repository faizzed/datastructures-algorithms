package datastructures.trie

import datastructures.file.FileReader

/**
* special characters, lower and upper case characters
* */
private const val CHARACTER_SPACE = 94

/**
 * Trie:
 * A trie is a like a binary tree, diff being it doesn't have to have two nodes. For a possible set of lookups each node may have that many children.
 * A trie store all kinds of patterns within its nodes.
 *
 * e.g dig, die, dip
 *      root
 *      |
 *      d
 *      |
 *      i
 *    | | | |
 *   g  e p s
 * each has a common pattern (this saves space in storing, and can help in suggesting possible combinations when di is detected)
 *
 * same as i, a root in possible searches of small letter strings (a..z) will have 26 possible children
 * in turn those 26 children will have their own 26 possible children.
 *
 * This can be implemented via arrays.
 *
 * To start a trie, we need to start with root, having a children array and a flag that says whether this node completes the word/tree.
* */
class Trie(private val character: String = "", private val pre: Trie? = null) {

    private var isWord = false
    private var children = arrayOfNulls<Trie>(CHARACTER_SPACE)

    /**
     * On getting a new word, start from root and subsequently check its children, if they have the key
     * simply skip, if they dont have it, initialize the character there.
     *
     * On the end of the word, mark the last node as the end of this tree.
    * */
    fun insert(word: String) {
        var current = this
        for (c in word.chars()) {
            if (current.children[c - 32] == null) {
                current.children[c - 32] = Trie(Character.toString(c), current)
            }

            current = current.children[c - 32]!!
        }
        current.isWord = true
    }

    /**
     * On searching for a key, start with first character and keep searching finding the next character in its
     * children, if we parse all the characters successfully, we should check whether the last node is end of this tree.
     *
     * If not, we didn't find the word. if yes we found the word.
    * */
    fun search(word: String): Boolean {
        var current = this
        for (c in word.chars()) {
            if (current.children[c - 32] == null) {
                return false
            }
            current = current.children[c - 32]!!
        }
        return current.isWord
    }

    /**
     * Return all the nodes that can be started with
     * the supplied pattern
     * e.g he
     * -> hey, hello
     *
     * parse the array until the end of the pattern
     * then go through all the children until they reach the end
     * then parse back through pre element to make the word
    * */
    fun autoComplete(word: String): ArrayList<String>? {
        var current = this
        for (c in word.chars()) {
            if (current.children[c - 32] == null) {
                return null
            }
            current = current.children[c - 32]!!
        }

        return current.getWords()
    }

    private fun getNonNullChildren(trie: Trie): MutableList<Trie> {
        val list = mutableListOf<Trie>()
        for (node in trie.children) {
            if (node != null) {
                list.add(node)
            }
        }
        return list
    }

    /**
    * What happens in getWords is really interesting:
     * Imagine Tree
     *          f
     *          |
     *          i
     *       |  |   |
     *       r  l   t
     *      |   |
     *      s   l
     *      |
     *      t
     *
     * suppose we wanted to see the possible letters with fi
     * then they will be first, fill, fit
     *
     * getWords starts with the current node being i
     * it visits each child until it reaches isWord. Meaning its the end of that word. It traverses back the name with toString method
     * Which lookup the pre node name until it reaches root.
     *
     * On reaching a word, it adds it to the list and return the list.
     *
     * Where does the list go???
     *
     * Since this is recursion, the (i) was the parent caller, it backtracks the call again to the (i) node,
     * add the result of the child node to its own list and call another of its child.
     *
     * When all the children are visited the (i) node has all the words from its children.
    * */
    fun getWords(): ArrayList<String> {
        val list = arrayListOf<String>()

        if (isWord) {
            list.add(toString())
        }

        for (node in getNonNullChildren(this)) {
            list.addAll(node.getWords())
        }

        return list
    }

    override fun toString(): String {
        return if (pre == null) {
            ""
        } else {
            pre.toString() + character
        }
    }
}

fun main() {
    val trie = Trie().apply {
        insert("paint")
        insert("painter")
        insert("painting")
        insert("fit")
        insert("fill")
        insert("first")
        insert("pause")
        insert("packet")
    }

    trie.also(::println)

    trie.search("paint").also(::println)
    trie.search("filter").also(::println)
    trie.autoComplete("fi").also(::println)
    trie.autoComplete("p").also(::println)
    trie.autoComplete("pain").also(::println)
    trie.autoComplete("pack").also(::println)

    val trie2 = Trie()
    FileReader().toTokens("./src/main/kotlin/datastructures/trie/file.txt").also {
        it.forEach { word ->
            trie2.insert(word)
        }
    }

    trie2.autoComplete("h").also(::println)
    trie2.autoComplete("e").also(::println)
    trie2.autoComplete("re").also(::println)
    trie2.autoComplete("a").also(::println)
    trie2.autoComplete("an").also(::println)
}