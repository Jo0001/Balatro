package de.htwg.se.balatro.model

import PokerHandTypes.*

case class PokerHands(hand: PokerHandTypes, validCards: List[Int], sortedCards: List[Card]) {
  val chips = hand match
    case STRAIGHT_FLUSH => 100
    case FOUR_OF_A_KIND => 60
    case FULL_HOUSE => 40
    case FLUSH => 35
    case STRAIGHT => 30
    case THREE_OF_A_KIND => 30
    case TWO_PAIR => 20
    case PAIR => 10
    case HIGH_CARD => 5
    case EMPTY => 0

  val mult = hand match
    case STRAIGHT_FLUSH => 8
    case FOUR_OF_A_KIND => 7
    case FULL_HOUSE => 4
    case FLUSH => 4
    case STRAIGHT => 4
    case THREE_OF_A_KIND => 3
    case TWO_PAIR => 2
    case PAIR => 2
    case HIGH_CARD => 1
    case EMPTY => 0

  val name = hand match
    case STRAIGHT_FLUSH => "Straight Flush"
    case FOUR_OF_A_KIND => "Four of a kind"
    case FULL_HOUSE => "Full House"
    case FLUSH => "Flush"
    case STRAIGHT => "Straight"
    case THREE_OF_A_KIND => "Three of a kind"
    case TWO_PAIR => "Two Pair"
    case PAIR => "Pair"
    case HIGH_CARD => "High Card"
    case EMPTY => ""

  val cardValues = validCards.map(i => sortedCards(i).value).sum

  val chipsTotal: Int = cardValues + chips
  val scoreTotal: Int = chipsTotal * mult
}

object PokerHands:
  def apply(cards: List[Card]): PokerHands = {
    val sorted = cards.sortBy(_.rank.ordinal)
    val (hand, validCards) = HandEvaluator.evaluate(sorted)
    PokerHands(hand, validCards, sorted)
  }




