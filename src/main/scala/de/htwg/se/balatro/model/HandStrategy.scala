package de.htwg.se.balatro.model


object HandUtil:
  def indicesOfGroupSize(sorted: List[Card], n: Int): List[Int] =
    sorted.zipWithIndex.collect {
      case (card, i) if sorted.count(_.rank == card.rank) == n => i
    }

  def isFlush(sorted: List[Card]): Boolean =
    sorted.length == 5 && sorted.forall(_.symbol == sorted.head.symbol)

  def isStraight(sorted: List[Card]): Boolean =
    val ranks = sorted.map(_.rank.ordinal)
    sorted.length == 5 && (
      ranks.zip(ranks.tail).forall((a, b) => a + 1 == b) ||
        ranks == List(0, 1, 2, 3, 12)
    )



trait HandStrategy:
  def handTypes: PokerHandTypes
  def evaluate(sorted: List[Card]): Option[List[Int]]

object StraightFlushStrategy extends HandStrategy:
  def handTypes =  PokerHandTypes.STRAIGHT_FLUSH
  def evaluate(sorted: List[Card]) =
    Option.when(HandUtil.isStraight(sorted) && HandUtil.isFlush(sorted))(List(0,1,2,3,4))

object FlushStrategy extends HandStrategy:
  def handTypes = PokerHandTypes.FLUSH
  def evaluate(sorted: List[Card]) =
    Option.when(HandUtil.isFlush(sorted))(List(0,1,2,3,4))

object StraightStrategy extends HandStrategy:
  def handTypes = PokerHandTypes.STRAIGHT
  def evaluate(sorted: List[Card]) =
    Option.when(HandUtil.isStraight(sorted))(List(0,1,2,3,4))

object FullHouseStrategy extends HandStrategy:
  def handTypes = PokerHandTypes.FULL_HOUSE
  def evaluate(sorted: List[Card]) =
    Option.when(HandUtil.indicesOfGroupSize(sorted, 3).nonEmpty &&
      HandUtil.indicesOfGroupSize(sorted, 2).nonEmpty)(List(0,1,2,3,4))

object FourOfAKindStrategy extends HandStrategy:
  def handTypes = PokerHandTypes.FOUR_OF_A_KIND
  def evaluate(sorted: List[Card]) =
    Some(HandUtil.indicesOfGroupSize(sorted, 4)).filter(_.nonEmpty)

object ThreeOfAKindStrategy extends HandStrategy:
  def handTypes = PokerHandTypes.THREE_OF_A_KIND
  def evaluate(sorted: List[Card]) =
    Some(HandUtil.indicesOfGroupSize(sorted, 3)).filter(_.nonEmpty)

object TwoPairStrategy extends HandStrategy:
  def handTypes = PokerHandTypes.TWO_PAIR
  def evaluate(sorted: List[Card]) =
    Some(HandUtil.indicesOfGroupSize(sorted, 2)).filter(_.length == 4)

object PairStrategy extends HandStrategy:
  def handTypes = PokerHandTypes.PAIR
  def evaluate(sorted: List[Card]) =
    Some(HandUtil.indicesOfGroupSize(sorted, 2)).filter(_.length == 2)

object HighCardStrategy extends HandStrategy:
  def handTypes = PokerHandTypes.HIGH_CARD
  def evaluate(sorted: List[Card]) =
    Option.when(sorted.nonEmpty)(List(sorted.length - 1))


object HandEvaluator:
  val strategies: List[HandStrategy] = List(
    StraightFlushStrategy, FlushStrategy, StraightStrategy, FullHouseStrategy,
    FourOfAKindStrategy, ThreeOfAKindStrategy,
    TwoPairStrategy, PairStrategy, HighCardStrategy
  )

  def evaluate(sorted: List[Card]): (PokerHandTypes, List[Int]) =
    strategies.view
      .flatMap(s => s.evaluate(sorted).map(idx => (s.handTypes, idx)))
      .headOption
      .getOrElse((PokerHandTypes.EMPTY, List()))

