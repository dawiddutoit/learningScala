import module2.week8.Pouring

object Test {
  val problem  = new Pouring(Vector(4, 9, 19))
  problem.moves

  problem.pathSets.take(3).toList
  problem.solutions(17)
}
