package mggger.io
import mggger.io.antlr.RomanBaseVisitor
import mggger.io.antlr.RomanParser

class RomanVisitor extends RomanBaseVisitor[Int] {

  override def visitAdd(ctx: RomanParser.AddContext): Int =
    visit(ctx.expr(0)) + visit(ctx.expr(1))

  override def visitSub(ctx: RomanParser.SubContext): Int =
    visit(ctx.expr(0)) - visit(ctx.expr(1))

  override def visitParens(ctx: RomanParser.ParensContext): Int =
    visit(ctx.expr())

  override def visitRoman(ctx: RomanParser.RomanContext): Int = {

    var digit = 0
    var ten = 0
    var hundred = 0
    var thousand = 0

    if (ctx.digit() != null) digit = visit(ctx.digit())

    if (ctx.ten() != null) ten = visit(ctx.ten())

    if (ctx.hundred() != null) hundred = visit(ctx.hundred())

    if (ctx.thousand() != null) thousand = visit(ctx.thousand())

    digit + ten + hundred + thousand
  }

  override def visitStat(ctx: RomanParser.StatContext): Int = {
    visit(ctx.expr())
  }

  override def visitDigit(ctx: RomanParser.DigitContext): Int =
    ctx.getText match {
      case "I"    => 1
      case "II"   => 2
      case "III"  => 3
      case "IV"   => 4
      case "V"    => 5
      case "VI"   => 6
      case "VII"  => 7
      case "VIII" => 8
      case "IX"   => 9
      case _      => 0
    }

  override def visitTen(ctx: RomanParser.TenContext): Int = ctx.getText match {
    case "X"    => 10
    case "XX"   => 20
    case "XXX"  => 30
    case "XL"   => 40
    case "L"    => 50
    case "LX"   => 60
    case "LXX"  => 70
    case "LXXX" => 80
    case "XC"   => 90
  }

  override def visitMul(ctx: RomanParser.MulContext): Int =
    visit(ctx.expr(0)) * visit(ctx.expr(1))

  override def visitDiv(
    ctx: RomanParser.DivContext
  ): Int = {
    visit(ctx.expr(0)) / visit(ctx.expr(1))
  }


  override def visitHundred(ctx: RomanParser.HundredContext): Int =
    ctx.getText match {
      case "C"    => 100
      case "CC"   => 200
      case "CCC"  => 300
      case "CD"   => 400
      case "D"    => 500
      case "DC"   => 600
      case "DCC"  => 700
      case "DCCC" => 800
      case "CM"   => 900
    }

  override def visitThousand(ctx: RomanParser.ThousandContext): Int =
    ctx.getText match {
      case "M"   => 1000
      case "MM"  => 2000
      case "MMM" => 3000
    }
}
