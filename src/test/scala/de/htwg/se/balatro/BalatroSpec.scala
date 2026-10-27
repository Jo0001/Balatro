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
      val card = Card(symbol = CardSymbol.SPADES, rank = CardRank.ACE)
      card.color should be(CardColor.BLACK)
    }
  }

  "Balatro" should {
    "have a Blue Clubs Card" in {
      val card = Card(symbol = CardSymbol.CLUBS, rank = CardRank.ACE)
      card.color should be(CardColor.BLUE)
    }
  }

  "Balatro" should {
    "have a Red Hearts Card" in {
      val card = Card(symbol = CardSymbol.HEARTS, rank = CardRank.ACE)
      card.color should be(CardColor.RED)
    }
  }

  "Balatro" should {
    "have a Yellow Diamonds Card" in {
      val card = Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE)
      card.color should be(CardColor.YELLOW)
    }
  }

  "Balatro" should {
    "have rank ace with value" in {
      val aceCard = Card(symbol = CardSymbol.SPADES, rank = CardRank.ACE)
      aceCard.value should be(11)
    }
  }

  "Balatro" should {
    "have rank King with value" in {
      val kingCard = Card(symbol = CardSymbol.SPADES, rank = CardRank.KING)
      kingCard.value should be(10)
    }
  }

  "Balatro" should {
    "have rank Queen with value" in {
      val queenCard = Card(symbol = CardSymbol.SPADES, rank = CardRank.QUEEN)
      queenCard.value should be(10)
    }
  }

  "Balatro" should {
    "have rank Jack with value" in {
      val jackCard = Card(symbol = CardSymbol.SPADES, rank = CardRank.JACK)
      jackCard.value should be(10)
    }
  }

  "Balatro" should {
    "have rank Ten with value" in {
      val tenCard = Card(symbol = CardSymbol.SPADES, rank = CardRank.TEN)
      tenCard.value should be(10)
    }
  }

  "Balatro" should {
    "have rank Nine with value" in {
      val nineCard = Card(symbol = CardSymbol.SPADES, rank = CardRank.NINE)
      nineCard.value should be(9)
    }
  }

  "Balatro" should {
    "have rank Eight with value" in {
      val eightCard = Card(symbol = CardSymbol.SPADES, rank = CardRank.EIGHT)
      eightCard.value should be(8)
    }
  }

  "Balatro" should {
    "have rank Seven with value" in {
      val sevenCard = Card(symbol = CardSymbol.SPADES, rank = CardRank.SEVEN)
      sevenCard.value should be(7)
    }
  }

  "Balatro" should {
    "have rank Six with value" in {
      val sixCard = Card(symbol = CardSymbol.SPADES, rank = CardRank.SIX)
      sixCard.value should be(6)
    }
  }

  "Balatro" should {
    "have rank Five with value" in {
      val fiveCard = Card(symbol = CardSymbol.SPADES, rank = CardRank.FIVE)
      fiveCard.value should be(5)
    }
  }

  "Balatro" should {
    "have rank Four with value" in {
      val fourCard = Card(symbol = CardSymbol.SPADES, rank = CardRank.FOUR)
      fourCard.value should be(4)
    }
  }

  "Balatro" should {
    "have rank Three with value" in {
      val threeCard = Card(symbol = CardSymbol.SPADES, rank = CardRank.THREE)
      threeCard.value should be(3)
    }
  }

  "Balatro" should {
    "have rank Two with value" in {
      val twoCard = Card(symbol = CardSymbol.SPADES, rank = CardRank.TWO)
      twoCard.value should be(2)
    }
  }

}
