package de.htwg.se.balatro

import de.htwg.se.balatro.PokerHandTypes.*

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

  val cardValues =
    (for i <- validCards.indices yield sortedCards(validCards(i)).value).sum
}

object PokerHands:
  def matchHandType(playedCards: List[Card]): (PokerHandTypes, List[Int]) = {
    val sorted = playedCards.sortBy(_.rank.ordinal)
    val length = sorted.length
    if (sorted.forall(_.symbol == sorted.head.symbol) && length == 5) {
      sorted.sliding(2).foreach {
        case List(current, next) if current.rank.ordinal + 1 == next.rank.ordinal
          || next.rank.ordinal - 12 == sorted.head.rank.ordinal =>
          ()
        case _ =>
          return (FLUSH, List(0, 1, 2, 3, 4)) //flush case
      }
      (STRAIGHT_FLUSH, List(0, 1, 2, 3, 4)) //straight flush case
    } else {
      val countFirst = sorted.count(_.rank == sorted.head.rank)
      val countLast = sorted.count(_.rank == sorted.last.rank)
      val tuple = templist(length, countFirst, countLast, sorted)
      val countThird = tuple(1).getOrElse(0)
      val countSecond = tuple(2).getOrElse(0)
      val kind = tuple(0)
      if (kind.contains(2) && kind.contains(3)) {
        return (FULL_HOUSE, List(0, 1, 2, 3, 4)) //fullhouse case
      } else if (countFirst == 4) {
        return (FOUR_OF_A_KIND, List(0, 1, 2, 3)) //four of a kind case 1
      } else if (countLast == 4) {
        return (FOUR_OF_A_KIND, List(1, 2, 3, 4)) //four of a kind case 2
      } else if (kind.contains(3)) {
        if (countFirst == 3) {
          return (THREE_OF_A_KIND, List(0, 1, 2)) //three of a kind case 1
        } else if (countSecond == 3) {
          return (THREE_OF_A_KIND, List(1, 2, 3)) //three of a kind case 2
        } else {
          return (THREE_OF_A_KIND, List(2, 3, 4)) //three of a kind case 3
        }
      } else if ((length == 4 && (countFirst == 2 && countLast == 2))
        || (length == 5 && ((countFirst == 2 && countLast == 2) || (countFirst == 2 && countThird == 2) || countLast == 2 && countThird == 2))) {
        if (countFirst == 2 && countThird == 2) {
          return (TWO_PAIR, List(0, 1, 2, 3)) //twopair case 1
        } else if (countFirst == 2 && countLast == 2) {
          return (TWO_PAIR, List(0, 1, 3, 4)) //twopair case 2
        } else if (countLast == 2 && countThird == 2) {
          return (TWO_PAIR, List(1, 2, 3, 4)) //twopair case 3
        }
      } else if (kind.contains(2)) {
        if (countFirst == 2) {
          return (PAIR, List(0, 1)) //pair case 1
        } else if (countSecond == 2) {
          return (PAIR, List(1, 2)) //pair case 2
        } else if (countThird == 2) {
          return (PAIR, List(2, 3)) //pair case 3
        } else {
          return (PAIR, List(3, 4)) //pair case 4
        }

      } else {
        sorted.sliding(2).foreach {
          case List(current, next) if current.rank.ordinal + 1 == next.rank.ordinal
            || next.rank.ordinal - 12 == sorted.head.rank.ordinal =>
            ()
          case _ =>
            return (HIGH_CARD, List(sorted.indexOf(sorted.last))) //highcard case
        }
      }
      (STRAIGHT, List(0, 1, 2, 3, 4)) //straight case
    }
  }

  def templist(length: Int, count1: Int, count2: Int, sorted: List[Card]): (List[Int], Option[Int], Option[Int]) = {
    if (length == 2) {
      val countPair = sorted.count(_.rank == sorted(1).rank)
      (List(count1, count2, countPair), None, Some(countPair))
    } else if (length >= 3) {
      val count3 = sorted.count(_.rank == sorted(2).rank)
      val countPair = sorted.count(_.rank == sorted(1).rank)
      (List(count1, count2, count3), Some(count3), Some(countPair))
    } else {
      (List(count1, count2), None, None)
    }
  }

  def apply(cards: List[Card]): PokerHands = {
    val tup = matchHandType(cards)
    new PokerHands(tup(0), tup(1), cards.sortBy(_.rank.ordinal))
  }




