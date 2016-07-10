package week3

class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be nonzero")

  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  val numer = x

  val denom = y

  def <(that: Rational): Boolean = numer * that.denom < that.numer * denom

  def max(that: Rational) = if (this < that) that else this

  def +(that: Rational): Rational =
    new Rational(
      this.numer * that.denom + that.numer * this.denom,
      this.denom * that.denom
    )



  def unary_- : Rational = new Rational(-numer, denom)

  def sub(that: Rational) = this + -that


  override def toString = {
    val divisor = gcd(numer, denom)
    numer / divisor + "/" + denom / divisor
  }
}
