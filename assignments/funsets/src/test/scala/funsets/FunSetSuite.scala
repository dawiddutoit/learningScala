package funsets

import org.scalatest.FunSuite
import FunSets._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
  * This class is a test suite for the methods in object FunSets. To run
  * the test suite, you can either:
  * - run the "test" command in the SBT console
  * - right-click the file in eclipse and chose "Run As" - "JUnit Test"
  */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
    * Link to the scaladoc - very clear and detailed tutorial of FunSuite
    *
    * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
    *
    * Operators
    * - test
    * - ignore
    * - pending
    */

  /**
    * Tests are written using the "test" operator and the "assert" method.
    */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
    * For ScalaTest tests, there exists a special equality operator "===" that
    * can be used inside "assert". If the assertion fails, the two values will
    * be printed in the error message. Otherwise, when using "==", the test
    * error message will only say "assertion failed", without showing the values.
    *
    * Try it out! Change the values so that the assertion fails, and look at the
    * error message.
    */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  test("contains should not have -50") {
    assert(!contains({ requested: Int => requested > 0 }, -50))
  }

  test("contains should have 50") {
    assert(contains({ requested: Int => requested > 0 }, 50))
  }

  /**
    * When writing tests, one would often like to re-use certain values for multiple
    * tests. For instance, we would like to create an Int-set and have multiple test
    * about it.
    *
    * Instead of copy-pasting the code for creating the set into every test, we can
    * store it in the test class using a val:
    *
    * val s1 = singletonSet(1)
    *
    * However, what happens if the method "singletonSet" has a bug and crashes? Then
    * the test methods are not even executed, because creating an instance of the
    * test class fails!
    *
    * Therefore, we put the shared values into a separate trait (traits are like
    * abstract classes), and create an instance inside each test method.
    *
    */

  trait TestSets {
    val singletonWith1 = singletonSet(1)
    val singletonWith2 = singletonSet(2)
    val singletonWith3 = singletonSet(3)
    val singletonWith4 = singletonSet(4)
    val singletonWith5 = singletonSet(5)

    val positiveValues = union(singletonSet(1), singletonSet(42))
    val evenValues = union(singletonSet(6), singletonSet(2))
    val negativeValues = union(singletonSet(-10), singletonSet(-666))
    val oddValues = union(singletonSet(3), singletonSet(9))
    val posAndNegValues = union(positiveValues, negativeValues)
    val evenOddValues = union(evenValues, oddValues)
  }

  /**
    * This test is currently disabled (by using "ignore") because the method
    * "singletonSet" is not yet implemented and the test would fail.
    *
    * Once you finish your implementation of "singletonSet", exchange the
    * function "ignore" by "test".
    */
  test("singletonSet(1) contains 1") {

    /**
      * We create a new instance of the "TestSets" trait, this gives us access
      * to the values "s1" to "s3".
      */
    new TestSets {
      val s1Value = 1
      /**
        * The string argument of "assert" is a message that is printed in case
        * the test fails. This helps identifying which assertion failed.
        */
      assert(contains(singletonWith1, s1Value), s"Singleton contains $s1Value")
    }
  }

  test("singletonSet(1) should not contain 2") {
    new TestSets {
      assert(!contains(singletonWith1, 2), "Singleton does not contain 2")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(singletonWith1, singletonWith2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")

    }
  }

  test("intersect contains elements in both sets") {
    new TestSets {
      val result = !contains(intersect(singletonWith1, singletonWith2), 1)
      assert(result, "Intersect 1 between singletonSet(1) and singletonSet(2)")

    }
  }

  test("intersect does not contain values from both sets") {
    new TestSets {
      val result = !contains(intersect(singletonWith1, singletonWith2), 3)
      assert(result, "Intersect 2 between singletonSet(1) and singletonSet(2)")
    }
  }


  test("diff returns the difference between the two sets") {
    new TestSets {
      val result = contains(diff(singletonWith1, singletonWith2),1)
      assert(result, "Diff 1 between singletonSet(1) and singletonSet(2)")
    }
  }
  test("diff difference between 1 and 1 does not contain 2") {
    new TestSets {
      val result = !contains(diff(singletonWith1, singletonWith1),1)
      assert(result, "Diff 2 between singletonSet(1) and singletonSet(s1)")
    }
  }



  test("filter returns the subset of one set for which a parameter function holds") {
    new TestSets {
      val result = contains(filter(singletonWith1, { elem: Int => elem < 2 }),1)
      assert(result)
}
  }
  test("filter does not return the subset of one set for which a parameter function holds") {
    new TestSets {
      val result = !contains(filter(singletonWith3, { elem: Int => elem > 5 }),3)
      assert(result)
    }
  }

  test("forall positiveValues function") {
    new TestSets {
      assert(forall(positiveValues, { elem: Int => elem > 0 }))
    }
  }
  test("forall negativeValues less than zero function") {
    new TestSets {
      assert(forall(negativeValues, { elem: Int => elem < 0 }))

    }
  }
  test("forall evenValues equals zero function") {
    new TestSets {
      assert(forall(evenValues, { elem: Int => (elem % 2) == 0 }))

    }
  }

  test("forall oddValues does not equal 0 function") {
    new TestSets {
      assert(forall(oddValues, { elem: Int => (elem % 200) != 0 }))
    }
  }

  test("forall evenOddValues less than 0 function") {
    new TestSets {
      assert(!forall(evenOddValues, { elem: Int => elem < 0 }))
    }
  }

  test("forall evenOddValues equals zero function") {
    new TestSets {
      assert(!forall(evenOddValues, { elem: Int => (elem % 2) == 0 }))
    }
  }

  test("exists function") {
    new TestSets {
      assert(exists(posAndNegValues, { elem: Int => elem > 0 }))
      assert(exists(evenOddValues, { elem: Int => (elem % 2) == 0 }))
    }
  }

  test("map oddSetToEven function") {
    new TestSets {
      val mapOddSetToEven = map(oddValues, { elem: Int => elem * 2 })
      printSet(mapOddSetToEven)
      assert(forall(mapOddSetToEven, { elem: Int => (elem % 2) == 0 }))
    }
  }
  test("map evenSet to Odd function") {
    new TestSets {
      val mapEvenSetToOdd = map(evenValues, { elem: Int => elem + 1 })
      printSet(mapEvenSetToOdd)
      assert(contains(mapEvenSetToOdd, 7) && contains(mapEvenSetToOdd, 3))

    }

  }

}
