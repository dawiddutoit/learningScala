
object listfuns {
  val additionalContribution = List((1, 2), (2, 3), (3, 4), (4, 5))

  val previousContributions = List((1, 2), (2, 3), (5, 6))
  val partitionPoint = List((2, 3))

  val alreadyDone = additionalContribution.filter(x => previousContributions.contains(x))
  val notDone = additionalContribution.filterNot(x => previousContributions.contains(x))
  val part = additionalContribution.partition(x => partitionPoint.contains(x))

  val x = additionalContribution.takeWhile(x => x._1 > 1)

  val data = (List("a", "a", "a", "b", "c", "c", "a"))

  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      val (first, rest) = xs span (y => y == x)
      first :: pack(rest)
  }

  val result = pack(data)

  def encode[T](xs: List[T]): List[(T, Int)] =
    pack(xs) map (ys => (ys.head, ys.length))


  encode(data)
}
