import module2.week9.{BankAccount, Signal}

object accounts{
  def consolidated(accts: List[BankAccount]): Signal[Int] =
    Signal(accts.map(_.balance()).sum)

  val a = new BankAccount()
  val b = new BankAccount()
  val c = consolidated(List(a,b))

  c()
 a deposit 20
  c()
b deposit 30
c()
  val exchange = Signal(246.00)
  val inDollar = Signal(c() * exchange())
  inDollar()
b withdraw 10
  inDollar()

}
