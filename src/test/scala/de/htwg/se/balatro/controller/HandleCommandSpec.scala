package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.util.{Action, Phase}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

import scala.util.{Failure, Try}

class HandleCommandSpec extends AnyWordSpec {

  // Test-Double: liefert absichtlich eine "fremde" Exception statt RoundError
  class FailingState(error: Throwable) extends GameState {
    def phase: Phase = Phase.Playing

    def handle(action: Action, game: Game): Try[(Game, GameState)] = Failure(error)
  }

  "HandleCommand" should {
    "rethrow unexpected failures that are not a RoundError" in {
      val controller = new Controller()
      val boom = new RuntimeException("unexpected failure")
      controller.currentState = FailingState(boom)

      val command = new HandleCommand(controller, Action.Play)

      val thrown = intercept[RuntimeException] {
        command.doStep()
      }
      thrown shouldBe boom
    }
  }
}