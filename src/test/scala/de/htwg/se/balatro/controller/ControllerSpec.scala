package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.util.Action.*
import de.htwg.se.balatro.util.{Action, Observer, Phase, RoundError}
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

import scala.util.{Failure, Random, Try}

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

  class FailingState(error: Throwable) extends GameState {
    def phase: Phase = Phase.Playing

    def handle(action: Action, game: Game): Try[(Game, GameState)] = Failure(error)
  }

  "Controller" should {

    "store a RoundError in lastError instead of throwing" in {
      val controller = new Controller()
      controller.currentState = FailingState(RoundError.NoCardsSelected)

      controller.handle(Action.Select(List(0)))

      controller.lastError shouldBe Some(RoundError.NoCardsSelected)
    }

    "rethrow unexpected failures that are not a RoundError" in {
      val controller = new Controller()
      val boom = new RuntimeException("unexpected failure")
      controller.currentState = FailingState(boom)

      val thrown = intercept[RuntimeException] {
        controller.handle(Action.Select(List(0)))
      }
      thrown shouldBe boom
    }
  }
}
