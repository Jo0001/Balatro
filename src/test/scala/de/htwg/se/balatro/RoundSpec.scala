package de.htwg.se.balatro

import de.htwg.se.balatro.BlindType.*
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

import scala.util.Random

class RoundSpec extends AnyWordSpec {
  val blind = Blind(Small, 3, 100, 1)
  val rand = Random(123)
  "Round" should {
    "have a select method to select single cards" in {
      val handcards = List(Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE), Card(symbol = CardSymbol.HEARTS, rank = CardRank.KING))
      val round = Round(handcards, blind = blind)
      round.select(List(0)) should be(Round(handcards = handcards, selected = List(0), blind = blind))
    }
  }
  "Round" should {
    "have a select method to select multiple cards" in {
      val handcards = List(Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE), Card(symbol = CardSymbol.HEARTS, rank = CardRank.KING))
      val round = Round(handcards, blind = blind)
      round.select(List(0, 1)) should be(Round(handcards = handcards, selected = List(1, 0), blind = blind))
    }
  }
  "Round" should {
    "have a select method to unselect single cards" in {
      val handcards = List(Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE), Card(symbol = CardSymbol.HEARTS, rank = CardRank.KING))
      val round = Round(handcards, selected = List(0), blind = blind)
      round.select(List(0)) should be(Round(handcards = handcards, selected = List(), blind = blind))
    }
  }
  "Round" should {
    "have a select method to unselect multiple cards" in {
      val handcards = List(Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE), Card(symbol = CardSymbol.HEARTS, rank = CardRank.KING))
      val round = Round(handcards, selected = List(1, 0), blind = blind)
      round.select(List(1, 0)) should be(Round(handcards = handcards, selected = List(), blind = blind))
    }
  }
  "Round" should {
    "have a method to discard cards" in {
      val handcards = List(Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE), Card(symbol = CardSymbol.HEARTS, rank = CardRank.KING))
      val round = Round(handcards, selected = List(1), blind = blind, rand = rand)
      val handcardsNew = List(Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE), Card(symbol = CardSymbol.CLUBS, rank = CardRank.TEN))
      val deckNew = Deck(Some(List(Card(symbol = CardSymbol.CLUBS, rank = CardRank.TEN))))
      round.discard() should be(Round(handcards = handcardsNew, selected = List(), discardCounter = 1, blind = blind, rand = rand, deck = deckNew))
    }
  }
  "Round" should {
    "have a method to discard cards which does nothing if no cards are selected" in {
      val handcards = List(Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE), Card(symbol = CardSymbol.HEARTS, rank = CardRank.KING))
      val round = Round(handcards, selected = List(), blind = blind)
      round.discard() should be(Round(handcards = handcards, selected = List(), blind = blind))
    }
  }
  "Round" should {
    "have a method to discard cards only with discardcounter < 4" in {
      val handcards = List(Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE), Card(symbol = CardSymbol.HEARTS, rank = CardRank.KING))
      val round = Round(handcards, selected = List(1), discardCounter = 4, blind = blind)
      round.discard() should be(Round(handcards = handcards, selected = List(1), discardCounter = 4, blind = blind))
    }
  }

}
