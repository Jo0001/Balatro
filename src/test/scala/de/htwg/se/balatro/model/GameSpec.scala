package de.htwg.se.balatro.model

import BlindType.*
import de.htwg.se.balatro.model.Game
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class GameSpec extends AnyWordSpec {

  val blind = Blind(Small, 3, 100, 1)
  
  "Game.start()" should {

    "start with round 1" in {
      val game = Game.start()
      game.roundCounter shouldBe 1
    }
  }

  "Game" should {

    "be able to play" in {
      val game = Game(currentRound = Round(List(), blind = blind))
      game.play()
      game.roundCounter shouldBe 1
      game.ante shouldBe 8
    }
  }

  "Game" should {

    "have ante 8" in {
      val game = Game(currentRound = Round(List(), blind = blind))
      game.ante shouldBe 8
    }
  }

  "Game" should {

    "be able to discard" in {
      val game = Game(currentRound = Round(List(), blind = blind))
      game.discard()
      game.roundCounter shouldBe 1
      game.ante shouldBe 8
    }
  }

  "Game" should {

    "be able to select" in {
      val game = Game(currentRound = Round(List(), blind = blind))
      game.select(List(1,2,3))
      game.roundCounter shouldBe 1
      game.ante shouldBe 8
    }
  }
  
}