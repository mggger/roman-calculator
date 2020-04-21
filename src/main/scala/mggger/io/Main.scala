package mggger.io

import mggger.io.antlr.RomanLexer
import mggger.io.antlr.RomanParser
import org.antlr.v4.runtime._

object Main {

  def converToRoman(num: Int): String = {

    if (num <= 0 || num > 3999) {
      return "Roman number didn't support zero or more than 3999"
    } else {

      val romanMap = Map(
        1 -> "I",
        2 -> "II",
        3 -> "III",
        4 -> "IV",
        5 -> "V",
        6 -> "VI",
        7 -> "VII",
        8 -> "VIII",
        9 -> "IX",
        10 -> "X",
        20 -> "XX",
        30 -> "XXX",
        40 -> "XL",
        50 -> "L",
        60 -> "LX",
        70 -> "LXX",
        80 -> "LXXX",
        90 -> "XC",
        100 -> "C",
        200 -> "CC",
        300 -> "CCC",
        400 -> "CD",
        500 -> "D",
        600 -> "DC",
        700 -> "DCC",
        800 -> "DCCC",
        900 -> "CM",
        1000 -> "M",
        2000 -> "MM",
        3000 -> "MMM"
      )
      var diver = num / 10
      var rate = 1
      var left = num - diver * 10

      var stringList: Array[String] = Array()

      stringList ++= romanMap.get(left)

      while (diver > 10) {
        rate *= 10
        val tmp = diver
        diver /= 10
        left = tmp - diver * 10
        stringList ++= romanMap.get(left * rate)
      }

      rate *= 10
      stringList ++= romanMap.get(diver * rate)
      stringList.reverse.reduce(_ + _)
    }
  }

  def main(args: Array[String]): Unit = {

    val repr = io.StdIn.readLine()


    val input = new ANTLRInputStream(repr)
    val lexer = new RomanLexer(input)

    val commonTokenStream = new CommonTokenStream(lexer)
    val parser = new RomanParser(commonTokenStream)

    val tree = parser.stat()

    val walker = new RomanVisitor
    println(converToRoman(walker.visit(tree)))
  }
}
