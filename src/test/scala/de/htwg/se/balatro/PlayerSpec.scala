package de.htwg.se.balatro

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PlayerSpec extends AnyWordSpec {
  "Player" should {
    "have a score of 0 by default" in {
      val player = Player(0)
      player.score shouldBe 0
    }
  }
  "Player" should {
    "have a score of 10" in {
      val player = Player(0)
      val playerNew = player.setScore(10)
      playerNew.score shouldBe 10
    }
  }
}
