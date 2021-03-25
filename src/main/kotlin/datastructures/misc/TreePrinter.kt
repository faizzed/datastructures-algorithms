package datastructures.misc

import java.util.ArrayList

interface PrintableNode<T> {
    val left: PrintableNode<T>?
    val right: PrintableNode<T>?
    fun getText(): String
}

class TreePrinter {
    companion object {
        fun <T> print(root: PrintableNode<T>?) {
            val lines: MutableList<List<String?>> = ArrayList()
            var level: MutableList<PrintableNode<T>?> = ArrayList()
            var next: MutableList<PrintableNode<T>?> = ArrayList()
            level.add(root)
            var nn = 1
            var widest = 0
            while (nn != 0) {
                val line: MutableList<String?> = ArrayList()
                nn = 0
                for (n in level) {
                    if (n == null) {
                        line.add(null)
                        next.add(null)
                        next.add(null)
                    } else {
                        val aa = n.getText()
                        line.add(aa)
                        if (aa.length > widest) widest = aa.length
                        next.add(n.left)
                        next.add(n.right)
                        if (n.left != null) nn++
                        if (n.right != null) nn++
                    }
                }
                if (widest % 2 == 1) widest++
                lines.add(line)
                val tmp = level
                level = next
                next = tmp
                next.clear()
            }
            var perpiece = lines[lines.size - 1].size * (widest - 1)
            for (i in lines.indices) {
                val line = lines[i]
                val hpw = Math.floor((perpiece / 2f).toDouble()).toInt() - 1
                if (i > 0) {
                    for (j in line.indices) {

                        // split node
                        var c = ' '
                        if (j % 2 == 1) {
                            if (line[j - 1] != null) {
                                c = if (line[j] != null) '┴' else '┘'
                            } else {
                                if (j < line.size && line[j] != null) c = '└'
                            }
                        }
                        print(c)

                        // lines and spaces
                        if (line[j] == null) {
                            for (k in 0 until perpiece - 1) {
                                print(" ")
                            }
                        } else {
                            for (k in 0 until hpw) {
                                print(if (j % 2 == 0) " " else "─")
                            }
                            print(if (j % 2 == 0) "┌" else "┐")
                            for (k in 0 until hpw) {
                                print(if (j % 2 == 0) "─" else " ")
                            }
                        }
                    }
                    println()
                }

                // print line of numbers
                for (j in line.indices) {
                    var f = line[j]
                    if (f == null) f = ""
                    val gap1 = Math.ceil((perpiece / 2f - f.length / 2f).toDouble()).toInt()
                    val gap2 = Math.floor((perpiece / 2f - f.length / 2f).toDouble()).toInt()

                    // a number
                    for (k in 0 until gap1) {
                        print(" ")
                    }
                    print(f)
                    for (k in 0 until gap2) {
                        print(" ")
                    }
                }
                println()
                perpiece /= 2
            }
        }
    }
}