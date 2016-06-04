import week3.Rational

object rationals {
  println("test")
  val x = new Rational(1, 3)
  val y = new Rational(5, 7)
  val z = new Rational(3, 2)
  x.numer
  x.denom
  x.add(y)

  x.sub(y).sub(z)
}
