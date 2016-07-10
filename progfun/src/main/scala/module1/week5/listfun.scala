
object listfun{
val nums = List(2, -4,5,7,1)

  val theOtherList = List(2,1)

  nums.filter(x => theOtherList.exists(_ == x))


}
