package de.htwg.se.balatro.util

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class ObservableSpec extends AnyWordSpec {
  "Observable" should {

    "notify an observer after adding it" in {
      val observable = new Observable()
      var updateCount = 0

      val observer = new Observer {
        override def update(): Unit = updateCount += 1
      }

      observable.add(observer)
      updateCount shouldBe 0

      observable.notifyObservers()
      updateCount shouldBe 1
    }

    "stop notifying an observer after removing it" in {
      val observable = new Observable()
      var updateCount = 0

      val observer = new Observer {
        override def update(): Unit = updateCount += 1
      }

      observable.add(observer)
      observable.notifyObservers()
      updateCount shouldBe 1

      observable.remove(observer)
      observable.notifyObservers()
      updateCount shouldBe 1
    }
  }
}