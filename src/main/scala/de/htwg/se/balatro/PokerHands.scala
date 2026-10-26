package de.htwg.se.balatro

import de.htwg.se.balatro.PokerHandTypes.*

case class PokerHands(hand: PokerHandTypes) {
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

  def matchHandType(playedCards: List[Card]): (Int, Int) = {
    val sorted = playedCards.sortBy(_.rank.ordinal)
    val length = sorted.length
    if (sorted.forall(_.symbol == sorted.head.symbol) && length == 5) {
      sorted.sliding(2).foreach {
        case List(current, next) if current.rank.ordinal + 1 == next.rank.ordinal =>
          ()
        case _ =>
          val flush = PokerHands(FLUSH)
          return (flush.chips, flush.mult) //flush case
      }
      val stra_flush = PokerHands(STRAIGHT_FLUSH)
      return (stra_flush.chips, stra_flush.mult) //straight flush case
    } else {
      val count1 = sorted.count(_.rank == sorted.head.rank)
      val count2 = sorted.count(_.rank == sorted.last.rank)
      val tuple = templist(length, count1, count2, sorted)
      val count3 = tuple(1).getOrElse(0)
      val kind = tuple(0)
      if (kind.contains(2) && kind.contains(3)) {
        val fullhouse = PokerHands(FULL_HOUSE)
        return (fullhouse.chips, fullhouse.mult) //fullhouse case
      } else if (kind.contains(4)) {
        val fou_o_k = PokerHands(FOUR_OF_A_KIND)
        return (fou_o_k.chips, fou_o_k.mult) //four of a kind case
      } else if (kind.contains(3)) {
        val thr_o_k = PokerHands(THREE_OF_A_KIND) //three of a kind case
        return (thr_o_k.chips, thr_o_k.mult)
      } else if (count1 == 2 && count2 == 2 || count1 == 2 && count3 == 2 || count2 == 2 && count3 == 2) {
        val twopair = PokerHands(TWO_PAIR)
        return (twopair.chips, twopair.mult) //twopair case
      } else if (kind.contains(2)) {
        val pair = PokerHands(PAIR)
        return (pair.chips, pair.mult) //pair case
      } else if (length == 5) {
        sorted.sliding(2).foreach {
          case List(current, next) if current.rank.ordinal + 1 == next.rank.ordinal =>
            ()
          case _ =>
            val highcard = PokerHands(HIGH_CARD)
            return (highcard.chips, highcard.mult) //highcard case
        }
      }
      val straight = PokerHands(STRAIGHT)
      (straight.chips, straight.mult)
    }
  }

  def templist(length: Int, count1: Int, count2: Int, sorted: List[Card]): (List[Int], Option[Int]) = {
    if (length >= 3) {
      val count3 = sorted.count(_.rank == sorted(2).rank)
      (List(count1, count2, count3), Some(count3))
    } else {
      (List(count1, count2), None)
    }
  }
}




