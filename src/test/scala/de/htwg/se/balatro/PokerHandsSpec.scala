package de.htwg.se.balatro

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PokerHandsSpec extends AnyWordSpec {

  "PokerHands" should {
    "be Straight Flush" in {
      val lst = List(Card(symbol = CardSymbol.SPADES, rank = CardRank.ACE), Card(symbol = CardSymbol.SPADES, rank = CardRank.KING),
        Card(symbol = CardSymbol.SPADES, rank = CardRank.QUEEN), Card(symbol = CardSymbol.SPADES, rank = CardRank.JACK),
        Card(symbol = CardSymbol.SPADES, rank = CardRank.TEN))
      val strFlush = PokerHands(lst)
      strFlush.chips should be(100)
      strFlush.mult should be(8)
      strFlush.name should be("Straight Flush")
      strFlush.cardValues should be(51)
    }
  }

  "PokerHands" should {
    "be Four of a kind var 1" in {
      val lst = List(Card(symbol = CardSymbol.SPADES, rank = CardRank.ACE), Card(symbol = CardSymbol.HEARTS, rank = CardRank.ACE),
        Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE), Card(symbol = CardSymbol.CLUBS, rank = CardRank.ACE))
      val four = PokerHands(lst)
      four.chips should be(60)
      four.mult should be(7)
      four.name should be("Four of a kind")
      four.cardValues should be(44)
    }
  }

  "PokerHands" should {
    "be Four of a kind var 2" in {
      val lst = List(Card(symbol = CardSymbol.SPADES, rank = CardRank.ACE), Card(symbol = CardSymbol.HEARTS, rank = CardRank.ACE),
        Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE), Card(symbol = CardSymbol.CLUBS, rank = CardRank.ACE),
        Card(symbol = CardSymbol.CLUBS, rank = CardRank.FOUR))
      val four = PokerHands(lst)
      four.chips should be(60)
      four.mult should be(7)
      four.name should be("Four of a kind")
      four.cardValues should be(44)
    }
  }

  "PokerHands" should {
    "be Full House" in {
      val lst = List(Card(symbol = CardSymbol.SPADES, rank = CardRank.ACE), Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE),
        Card(symbol = CardSymbol.HEARTS, rank = CardRank.ACE), Card(symbol = CardSymbol.SPADES, rank = CardRank.KING),
        Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.KING))
      val fullhouse = PokerHands(lst)
      fullhouse.chips should be(40)
      fullhouse.mult should be(4)
      fullhouse.name should be("Full House")
      fullhouse.cardValues should be(53)
    }
  }

  "PokerHands" should {
    "be Flush" in {
      val lst = List(Card(symbol = CardSymbol.SPADES, rank = CardRank.ACE), Card(symbol = CardSymbol.SPADES, rank = CardRank.KING),
        Card(symbol = CardSymbol.SPADES, rank = CardRank.QUEEN), Card(symbol = CardSymbol.SPADES, rank = CardRank.THREE),
        Card(symbol = CardSymbol.SPADES, rank = CardRank.TWO))
      val flush = PokerHands(lst)
      flush.chips should be(35)
      flush.mult should be(4)
      flush.name should be("Flush")
      flush.cardValues should be(36)
    }
  }

  "PokerHands" should {
    "be Straight var 1" in {
      val lst = List(Card(symbol = CardSymbol.SPADES, rank = CardRank.ACE), Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.KING),
        Card(symbol = CardSymbol.HEARTS, rank = CardRank.QUEEN), Card(symbol = CardSymbol.SPADES, rank = CardRank.JACK),
        Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.TEN))
      val straight = PokerHands(lst)
      straight.chips should be(30)
      straight.mult should be(4)
      straight.name should be("Straight")
      straight.cardValues should be(51)
    }
  }

  "PokerHands" should {
    "be Straight var 2" in {
      val lst = List(Card(symbol = CardSymbol.SPADES, rank = CardRank.ACE), Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.FIVE),
        Card(symbol = CardSymbol.HEARTS, rank = CardRank.FOUR), Card(symbol = CardSymbol.SPADES, rank = CardRank.THREE),
        Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.TWO))
      val straight = PokerHands(lst)
      straight.chips should be(30)
      straight.mult should be(4)
      straight.name should be("Straight")
      straight.cardValues should be(25)
    }
  }

  "PokerHands" should {
    "be Three of a kind var 1" in {
      val lst = List(Card(symbol = CardSymbol.SPADES, rank = CardRank.ACE), Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.KING),
        Card(symbol = CardSymbol.HEARTS, rank = CardRank.THREE), Card(symbol = CardSymbol.SPADES, rank = CardRank.THREE),
        Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.THREE))
      val three = PokerHands(lst)
      three.chips should be(30)
      three.mult should be(3)
      three.name should be("Three of a kind")
      three.cardValues should be(9)
    }
  }

  "PokerHands" should {
    "be Three of a kind var 2" in {
      val lst = List(Card(symbol = CardSymbol.SPADES, rank = CardRank.ACE), Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.TWO),
        Card(symbol = CardSymbol.HEARTS, rank = CardRank.THREE), Card(symbol = CardSymbol.SPADES, rank = CardRank.THREE),
        Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.THREE))
      val three = PokerHands(lst)
      three.chips should be(30)
      three.mult should be(3)
      three.name should be("Three of a kind")
      three.cardValues should be(9)
    }
  }

  "PokerHands" should {
    "be Three of a kind var 3" in {
      val lst = List(Card(symbol = CardSymbol.SPADES, rank = CardRank.ACE), Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE),
        Card(symbol = CardSymbol.HEARTS, rank = CardRank.ACE), Card(symbol = CardSymbol.SPADES, rank = CardRank.TWO),
        Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.THREE))
      val three = PokerHands(lst)
      three.chips should be(30)
      three.mult should be(3)
      three.name should be("Three of a kind")
      three.cardValues should be(33)
    }
  }

  "PokerHands" should {
    "be Two Pair var 1" in {
      val lst = List(Card(symbol = CardSymbol.SPADES, rank = CardRank.ACE), Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.KING),
        Card(symbol = CardSymbol.HEARTS, rank = CardRank.KING), Card(symbol = CardSymbol.SPADES, rank = CardRank.THREE),
        Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.THREE))
      val two_pair = PokerHands(lst)
      two_pair.chips should be(20)
      two_pair.mult should be(2)
      two_pair.name should be("Two Pair")
      two_pair.cardValues should be(26)
    }
  }

  "PokerHands" should {
    "be Two Pair var 1.2" in {
      val lst = List(Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.KING),
        Card(symbol = CardSymbol.HEARTS, rank = CardRank.KING), Card(symbol = CardSymbol.SPADES, rank = CardRank.THREE),
        Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.THREE))
      val two_pair = PokerHands(lst)
      two_pair.chips should be(20)
      two_pair.mult should be(2)
      two_pair.name should be("Two Pair")
      two_pair.cardValues should be(26)
    }
  }

  "PokerHands" should {
    "be Two Pair var 2" in {
      val lst = List(Card(symbol = CardSymbol.SPADES, rank = CardRank.THREE), Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.THREE),
        Card(symbol = CardSymbol.HEARTS, rank = CardRank.FOUR), Card(symbol = CardSymbol.SPADES, rank = CardRank.KING),
        Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.KING))
      val two_pair = PokerHands(lst)
      two_pair.chips should be(20)
      two_pair.mult should be(2)
      two_pair.name should be("Two Pair")
      two_pair.cardValues should be(26)
    }
  }

  "PokerHands" should {
    "be Two Pair var 3" in {
      val lst = List(Card(symbol = CardSymbol.SPADES, rank = CardRank.THREE), Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.FOUR),
        Card(symbol = CardSymbol.HEARTS, rank = CardRank.FOUR), Card(symbol = CardSymbol.SPADES, rank = CardRank.KING),
        Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.KING))
      val two_pair = PokerHands(lst)
      two_pair.chips should be(20)
      two_pair.mult should be(2)
      two_pair.name should be("Two Pair")
      two_pair.cardValues should be(28)
    }
  }

  "PokerHands" should {
    "be Pair var 1" in {
      val lst = List(Card(symbol = CardSymbol.SPADES, rank = CardRank.ACE), Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE))
      val pair = PokerHands(lst)
      pair.chips should be(10)
      pair.mult should be(2)
      pair.name should be("Pair")
      pair.cardValues should be(22)
    }
  }

  "PokerHands" should {
    "be Pair var 2" in {
      val lst = List(Card(symbol = CardSymbol.SPADES, rank = CardRank.ACE), Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE),
        Card(symbol = CardSymbol.HEARTS, rank = CardRank.FOUR))
      val pair = PokerHands(lst)
      pair.chips should be(10)
      pair.mult should be(2)
      pair.name should be("Pair")
      pair.cardValues should be(22)
    }
  }

  "PokerHands" should {
    "be Pair var 3" in {
      val lst = List(Card(symbol = CardSymbol.SPADES, rank = CardRank.ACE), Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE),
        Card(symbol = CardSymbol.HEARTS, rank = CardRank.FOUR), Card(symbol = CardSymbol.SPADES, rank = CardRank.FIVE))
      val pair = PokerHands(lst)
      pair.chips should be(10)
      pair.mult should be(2)
      pair.name should be("Pair")
      pair.cardValues should be(22)
    }
  }

  "PokerHands" should {
    "be Pair var 4" in {
      val lst = List(Card(symbol = CardSymbol.SPADES, rank = CardRank.ACE), Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.ACE),
        Card(symbol = CardSymbol.HEARTS, rank = CardRank.FOUR), Card(symbol = CardSymbol.SPADES, rank = CardRank.FIVE),
        Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.THREE))
      val pair = PokerHands(lst)
      pair.chips should be(10)
      pair.mult should be(2)
      pair.name should be("Pair")
      pair.cardValues should be(22)
    }
  }

  "PokerHands" should {
    "be High Card" in {
      val lst = List(Card(symbol = CardSymbol.SPADES, rank = CardRank.ACE), Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.KING),
        Card(symbol = CardSymbol.HEARTS, rank = CardRank.TWO), Card(symbol = CardSymbol.SPADES, rank = CardRank.EIGHT),
        Card(symbol = CardSymbol.DIAMONDS, rank = CardRank.THREE))
      val highcard = PokerHands(lst)
      highcard.chips should be(5)
      highcard.mult should be(1)
      highcard.name should be("High Card")
      highcard.cardValues should be(11)
    }
  }

  "PokerHands" should {
    "be Empty" in {
      val empty = new PokerHands(PokerHandTypes.EMPTY, List(), List())
      empty.chips should be(0)
      empty.mult should be(0)
      empty.name should be("")
      empty.cardValues should be(0)
    }
  }


}
