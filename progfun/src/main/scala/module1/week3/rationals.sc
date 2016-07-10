import week3.Rational

object rationals {
  println("test")
  val x = new Rational(1, 3)
  val y = new Rational(5, 7)
  val z = new Rational(3, 2)

  x.numer
  x.denom
  x + y

  val a = new Rational(10, 20)
  a.numer
  a.denom

  x.sub(y).sub(z)
  y + y
  x < y
  x.max(y)

  x + y
//val strange = new Rational(1,0)
//  strange.add(strange)
  new Rational(2)
}
