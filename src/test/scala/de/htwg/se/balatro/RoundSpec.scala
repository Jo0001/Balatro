package de.htwg.se.balatro

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class RoundSpec extends AnyWordSpec {
  "Round" should {
    "have a select method to select single cards" in {
      val handcards = List(Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE), Card(symbol = CardSymbol.HEARTS, rank = CardRank.KING))
      val round = Round(handcards)
      round.select(List(0)) should be(Round(handcards = handcards, selected = List(0)))
    }
  }
  "Round" should {
    "have a select method to select multiple cards" in {
      val handcards = List(Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE), Card(symbol = CardSymbol.HEARTS, rank = CardRank.KING))
      val round = Round(handcards)
      round.select(List(0, 1)) should be(Round(handcards = handcards, selected = List(1, 0)))
    }
  }
  "Round" should {
    "have a select method to unselect single cards" in {
      val handcards = List(Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE), Card(symbol = CardSymbol.HEARTS, rank = CardRank.KING))
      val round = Round(handcards, selected = List(0))
      round.select(List(0)) should be(Round(handcards = handcards, selected = List()))
    }
  }
  "Round" should {
    "have a select method to unselect multiple cards" in {
      val handcards = List(Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE), Card(symbol = CardSymbol.HEARTS, rank = CardRank.KING))
      val round = Round(handcards, selected = List(1, 0))
      round.select(List(1, 0)) should be(Round(handcards = handcards, selected = List()))
    }
  }
  "Round" should {
    "have a method to discard cards" in {
      val handcards = List(Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE), Card(symbol = CardSymbol.HEARTS, rank = CardRank.KING))
      val round = Round(handcards, selected = List(1))
      val handcardsNew = List(Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE), Card(symbol = CardSymbol.SPADES, rank = CardRank.ACE))
      round.discard() should be(Round(handcards = handcardsNew, selected = List(), discardCounter = 1))
    }
  }
  "Round" should {
    "have a method to discard cards only with selected" in {
      val handcards = List(Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE), Card(symbol = CardSymbol.HEARTS, rank = CardRank.KING))
      val round = Round(handcards, selected = List())
      round.discard() should be(Round(handcards = handcards, selected = List()))
    }
  }
  "Round" should {
    "have a method to discard cards only with discardcounter < 3" in {
      val handcards = List(Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE), Card(symbol = CardSymbol.HEARTS, rank = CardRank.KING))
      val round = Round(handcards, selected = List(1), discardCounter = 3)
      round.discard() should be(Round(handcards = handcards, selected = List(1), discardCounter = 3))
    }
  }

}
