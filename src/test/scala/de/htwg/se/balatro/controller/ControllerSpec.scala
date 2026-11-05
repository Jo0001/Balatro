package de.htwg.se.balatro.controller

import de.htwg.se.balatro.util.Action.Select
import de.htwg.se.balatro.util.Observer
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec {
  "Controller" should {
    "update the game and notify observers when a selection changes" in {
      val controller = Controller()
      var updateCount = 0
      controller.add(new Observer {
        override def update(): Unit = updateCount += 1
      })

      controller.handle(Select(List(1)))
      updateCount shouldBe 1
    }
  }
}
