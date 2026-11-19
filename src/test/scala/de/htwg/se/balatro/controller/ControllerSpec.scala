package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.util.Action.*
import de.htwg.se.balatro.util.{Observer, Phase}
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

import scala.util.Random

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

    "undo, redo, restart and provide a snapshot" in {
      val controller = Controller(Game.start(new Random(42)))

      controller.handle(Select(List(0)))
      val before = controller.currentGame

      controller.handle(Discard) // case _ -> doStep
      val after = controller.currentGame
      after should not be before

      controller.handle(Undo) // undoStep
      controller.currentGame shouldBe before

      controller.handle(Redo) // redoStep
      controller.currentGame shouldBe after

      controller.handle(Restart) // applyDirectly + clear

      val snapshot = controller.gameSnapshot // gameSnapshot + createHandCards
      snapshot.phase shouldBe Phase.Playing
      snapshot.handcards.size shouldBe 8
    }
  }
}
