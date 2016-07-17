package module2.week8

/**
  * Created by dawid on 7/11/16.
  */
trait Tree {

  case class Inner(left: Tree, right: Tree) extends Tree

  case class Leaf(x: Int) extends Tree

}
