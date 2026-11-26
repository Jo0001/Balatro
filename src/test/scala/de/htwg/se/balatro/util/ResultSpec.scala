package de.htwg.se.balatro.util

import de.htwg.se.balatro.util.Result.*
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class ResultSpec extends AnyWordSpec {

  // two small steps: the first one can fail, the second one too
  val half: Int => Result[Int] = n => if (n % 2 == 0) Ok(n / 2) else Err(s"$n is odd")
  val positive: Int => Result[Int] = n => if (n > 0) Ok(n) else Err(s"$n is not positive")

  val okValue: Result[Int] = Ok(8)
  val errValue: Result[Int] = Err("broken")

  "Result.map" should {
    "change the value of an Ok" in {
      Ok(5).map(_ * 2) should be(Ok(10))
    }
    "change the type of the value" in {
      Ok(5).map(_.toString) should be(Ok("5"))
    }
    "leave an Err untouched and never call the function" in {
      var called = false
      val result = errValue.map { n => called = true; n * 2 }
      result should be(Err("broken"))
      called should be(false)
    }
  }

  "Result.flatMap" should {
    "pass the value of an Ok to the next step" in {
      Ok(8).flatMap(half) should be(Ok(4))
    }
    "return the Err of the next step" in {
      Ok(7).flatMap(half) should be(Err("7 is odd"))
    }
    "skip the next step when it is already an Err" in {
      var called = false
      val result = errValue.flatMap { n => called = true; Ok(n) }
      result should be(Err("broken"))
      called should be(false)
    }
    "stop at the first Err in a chain" in {
      Ok(6).flatMap(half).flatMap(half) should be(Err("3 is odd"))
    }
  }

  "Result in a for comprehension" should {
    "run all steps when everything is Ok" in {
      val result = for
        a <- Ok(8)
        b <- half(a)
        c <- half(b)
      yield c
      result should be(Ok(2))
    }
    "return the first Err" in {
      val result = for
        a <- Ok(6)
        b <- half(a)
        c <- half(b)
      yield c
      result should be(Err("3 is odd"))
    }
  }

  "The monad laws" should {
    "hold for left identity: Ok(a).flatMap(f) == f(a)" in {
      Ok(8).flatMap(half) should be(half(8))
      Ok(7).flatMap(half) should be(half(7))
      Ok(-4).flatMap(positive) should be(positive(-4))
    }
    "hold for right identity: m.flatMap(Ok(_)) == m" in {
      okValue.flatMap(Ok(_)) should be(okValue)
      errValue.flatMap(Ok(_)) should be(errValue)
    }
    "hold for associativity: m.flatMap(f).flatMap(g) == m.flatMap(a => f(a).flatMap(g))" in {
      val inputs: List[Result[Int]] = List(Ok(8), Ok(6), Ok(7), Ok(-4), Err("broken"))
      inputs.foreach { m =>
        m.flatMap(half).flatMap(positive) should be(m.flatMap(a => half(a).flatMap(positive)))
        m.flatMap(positive).flatMap(half) should be(m.flatMap(a => positive(a).flatMap(half)))
      }
    }
  }
}
