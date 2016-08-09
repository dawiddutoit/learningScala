package module3.week1

trait Task[A] {
def join: A

  implicit def getJoin[T](x:Task[T]): T = x.join

}
