package de.htwg.se.balatro.util

import de.htwg.se.balatro.model.BlindType.*
import de.htwg.se.balatro.model.{Blind, Game, Round}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.util.{Failure, Success}

class MockGameStorageStrategySpec extends AnyWordSpec with Matchers {

  val blind: Blind = Blind(Small, 3, 100, 1)
  val game1: Game = Game(currentRound = Round(List(), blind = blind))
  val game2: Game = game1.copy(roundCounter = 2)

  "A MockGameStorageStrategy" when {

    "created without an initial game" should {
      "fail to load with a NoSuchElementException" in {
        val storage = new MockGameStorageStrategy()
        val result = storage.load()
        result shouldBe a[Failure[?]]
        result.failed.get shouldBe a[NoSuchElementException]
        result.failed.get.getMessage shouldBe "no gamestate"
      }
    }

    "created with an initial game" should {
      "load that game" in {
        val storage = new MockGameStorageStrategy(Some(game1))
        storage.load() shouldBe Success(game1)
      }
    }

    "save is called" should {
      "return Success" in {
        val storage = new MockGameStorageStrategy()
        storage.save(game1) shouldBe Success(())
      }
      "make the game loadable" in {
        val storage = new MockGameStorageStrategy()
        storage.save(game1)
        storage.load() shouldBe Success(game1)
      }
      "overwrite a previously stored game" in {
        val storage = new MockGameStorageStrategy(Some(game1))
        storage.save(game2)
        storage.load() shouldBe Success(game2)
      }
    }
  }
}
