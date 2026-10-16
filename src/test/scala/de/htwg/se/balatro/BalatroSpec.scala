package de.htwg.se.balatro

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class BalatroSpec extends AnyWordSpec {
  "Balatro" should {
    "run a test" in {
      1 shouldBe 1
    }
  }

  "Balatro" should {
    "have a Black Spades Card" in {
      val blackspade = Card(symbol = CardSymbol.SPADES, rank = Rank.ACE)
      blackspade.color should be(CardColor.BLACK)
    }
  }
}
