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
      val card = Card(symbol = CardSymbol.SPADES, rank = Rank.ACE)
      card.color should be(CardColor.BLACK)
    }
  }

  "Balatro" should {
    "have a Blue Clubs Card" in {
      val card = Card(symbol = CardSymbol.CLUBS, rank = Rank.ACE)
      card.color should be(CardColor.BLUE)
    }
  }

  "Balatro" should {
    "have a Red Hearts Card" in {
      val card = Card(symbol = CardSymbol.HEARTS, rank = Rank.ACE)
      card.color should be(CardColor.RED)
    }
  }

  "Balatro" should {
    "have a Yellow Diamonds Card" in {
      val card = Card(symbol = CardSymbol.DIAMONDS, rank = Rank.ACE)
      card.color should be(CardColor.YELLOW)
    }
  }
}
