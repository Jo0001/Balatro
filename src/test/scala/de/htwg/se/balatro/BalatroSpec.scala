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

  "Balatro" should {
    "have rank ace with value" in {
      val aceCard = Card(symbol = CardSymbol.SPADES, rank = Rank.ACE)
      aceCard.value should be(11)
    }
  }

  "Balatro" should {
    "have rank King with value" in {
      val KingCard = Card(symbol = CardSymbol.SPADES, rank = Rank.KING)
      KingCard.value should be(10)
    }
  }

  "Balatro" should {
    "have rank Queen with value" in {
      val QueenCard = Card(symbol = CardSymbol.SPADES, rank = Rank.QUEEN)
      QueenCard.value should be(10)
    }
  }

  "Balatro" should {
    "have rank Jack with value" in {
      val JackCard = Card(symbol = CardSymbol.SPADES, rank = Rank.JACK)
      JackCard.value should be(10)
    }
  }

  "Balatro" should {
    "have rank Ten with value" in {
      val TenCard = Card(symbol = CardSymbol.SPADES, rank = Rank.TEN)
      TenCard.value should be(10)
    }
  }

  "Balatro" should {
    "have rank Nine with value" in {
      val NineCard = Card(symbol = CardSymbol.SPADES, rank = Rank.NINE)
      NineCard.value should be(9)
    }
  }

  "Balatro" should {
    "have rank Eight with value" in {
      val EightCard = Card(symbol = CardSymbol.SPADES, rank = Rank.EIGHT)
      EightCard.value should be(8)
    }
  }

  "Balatro" should {
    "have rank Seven with value" in {
      val SevenCard = Card(symbol = CardSymbol.SPADES, rank = Rank.SEVEN)
      SevenCard.value should be(7)
    }
  }

  "Balatro" should {
    "have rank Six with value" in {
      val SixCard = Card(symbol = CardSymbol.SPADES, rank = Rank.SIX)
      SixCard.value should be(6)
    }
  }

  "Balatro" should {
    "have rank Five with value" in {
      val FiveCard = Card(symbol = CardSymbol.SPADES, rank = Rank.FIVE)
      FiveCard.value should be(5)
    }
  }

  "Balatro" should {
    "have rank Four with value" in {
      val FourCard = Card(symbol = CardSymbol.SPADES, rank = Rank.FOUR)
      FourCard.value should be(4)
    }
  }

  "Balatro" should {
    "have rank Three with value" in {
      val ThreeCard = Card(symbol = CardSymbol.SPADES, rank = Rank.THREE)
      ThreeCard.value should be(3)
    }
  }

  "Balatro" should {
    "have rank Two with value" in {
      val TwoCard = Card(symbol = CardSymbol.SPADES, rank = Rank.TWO)
      TwoCard.value should be(2)
    }
  }

  "Balatro" should {
    "have a title with correct indentation" in {
      val title = "Small Blind"
      val fullTitle = createTitle(title, "<Score 20/300> || <Reward 3$> || <Ante 1/8> || <Round 1>", Console.RESET)
      val indentationSpaces = fullTitle.count(_ == ' ') - title.count(_ == ' ')
      indentationSpaces should be(23)
    }
  }
}
