package de.htwg.se.balatro.model

import de.htwg.se.balatro.model.Player
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

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
