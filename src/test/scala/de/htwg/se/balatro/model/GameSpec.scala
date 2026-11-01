package de.htwg.se.balatro.model

import de.htwg.se.balatro.model.Game
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class GameSpec extends AnyWordSpec {
  "Game.start()" should {

    "start with round 1" in {
      val game = Game.start()
      game.roundCounter shouldBe 1
    }
  }
}