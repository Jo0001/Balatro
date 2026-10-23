package de.htwg.se.balatro

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PlayerSpec extends AnyWordSpec {
  "Player" should {
    "have a money of 0 by default" in {
      val player = Player(0)
      player.money shouldBe 0
    }
  }
  "Player" should {
    "have a money of 10" in {
      val player = Player(0).copy(money = 10)
      player.money shouldBe 10
    }
  }
}
