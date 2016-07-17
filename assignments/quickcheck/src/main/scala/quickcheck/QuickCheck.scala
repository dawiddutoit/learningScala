package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  property("minimum") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }

  property("order") = forAll { (a: Int, b: Int) =>
    val h = insert(a, insert(b, empty))
    findMin(h) == (if (a > b) b else a)
  }

  property("empty") = forAll { a: Int =>
    val emp = insert(a, empty)
    deleteMin(emp) == empty
  }


  property("order of mins") = forAll { (h: H) =>
    toList(h).zip(toList(h).drop(1)).forall {
      case (x, y) => x <= y
    }
  }
  property("melding") = forAll { (a: Int, b: Int) =>
    val h = insert(a, empty)
    val i = insert(b, empty)
    val merged = meld(h, i)

    findMin(merged) == (if (a > b) b else a)
  }

  property("meld heaps together") = forAll { (a: H, b: H) =>
    val minA = findMin(a)
    val minB = findMin(b)
    val merged = meld(a, b)
    findMin(merged) == (if (minA < minB) minA else minB)
  }

  property("associative meld") = forAll { (a: H, b: H, c: H) =>
    val first = meld(meld(a, b), c)
    val second = meld(a, meld(b, c))
    toList(first) == toList(second)
  }

  property("remove") = forAll { (a: H, b: H) =>
    val first = findMin(a)
    val second = findMin(b)
    val min = first.min(second)
    findMin(meld(deleteMin(a), insert(min, b))) == min
  }

  lazy val genHeap: Gen[H] = for {
    n <- arbitrary[A]
    h <- frequency((1, Gen.const(empty)), (9, genHeap))
  } yield insert(n, h)


  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  def toList(h: H): List[Int] = if (isEmpty(h)) Nil else findMin(h) :: toList(deleteMin(h))
}
