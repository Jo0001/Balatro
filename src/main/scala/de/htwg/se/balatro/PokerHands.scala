package de.htwg.se.balatro

import de.htwg.se.balatro.PokerHandTypes.*

case class PokerHands(hand: PokerHandTypes, cards: List[Card]) {
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
}

object PokerHands:
  def matchHandType(playedCards: List[Card]): PokerHandTypes = {
    val sorted = playedCards.sortBy(_.rank.ordinal)
    val length = sorted.length
    if (sorted.forall(_.symbol == sorted.head.symbol) && length == 5) {
      sorted.sliding(2).foreach {
        case List(current, next) if current.rank.ordinal + 1 == next.rank.ordinal =>
          ()
        case _ =>
          return FLUSH //flush case
      }
      STRAIGHT_FLUSH //straight flush case
    } else {
      val count1 = sorted.count(_.rank == sorted.head.rank)
      val count2 = sorted.count(_.rank == sorted.last.rank)
      val tuple = templist(length, count1, count2, sorted)
      val count3 = tuple(1).getOrElse(0)
      val kind = tuple(0)
      if (kind.contains(2) && kind.contains(3)) {
        return FULL_HOUSE //fullhouse case
      } else if (kind.contains(4)) {
        return FOUR_OF_A_KIND //four of a kind case
      } else if (kind.contains(3)) {
        return THREE_OF_A_KIND //three of a kind case
      } else if (count1 == 2 && count2 == 2 || count1 == 2 && count3 == 2 || count2 == 2 && count3 == 2) {
        return TWO_PAIR //twopair case
      } else if (kind.contains(2)) {
        return PAIR //pair case
      } else {
        sorted.sliding(2).foreach {
          case List(current, next) if current.rank.ordinal + 1 == next.rank.ordinal =>
            ()
          case _ =>
            return HIGH_CARD //highcard case
        }
      }
      return STRAIGHT //straight case
    }
  }

  def apply(cards: List[Card]): PokerHands =
    new PokerHands(matchHandType(cards), cards)

  def templist(length: Int, count1: Int, count2: Int, sorted: List[Card]): (List[Int], Option[Int]) = {
    if (length >= 3) {
      val count3 = sorted.count(_.rank == sorted(2).rank)
      (List(count1, count2, count3), Some(count3))
    } else {
      (List(count1, count2), None)
    }
  }




