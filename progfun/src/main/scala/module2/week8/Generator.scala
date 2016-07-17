package module2.week8

/**
  * Created by dawid on 7/11/16.
  */
trait Generator[+T] {
  self =>
  def generate: T

  val integers = new Generator[Int] {
    def generate = scala.util.Random.nextInt()
  }

  def map[S](f: T => S): Generator[S] = new Generator[S] {
    def generate = f(self.generate)
  }

  def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
    def generate = f(self.generate).generate
  }

  def pairs[T, U](t: Generator[T], u: Generator[U]) = t flatMap {
    x => u map { y => (x, y) }
  }

  def single[T](x: T): Generator[T] = new Generator[T] {
    def generate = x
  }

  def choose(lo: Int, hi: Int): Generator[Int] =
    for (x <- integers) yield lo + x % (hi - lo)

  def oneOf[T](xs: T*): Generator[T] =
    for (idx <- choose(0, xs.length)) yield xs(idx)


}
