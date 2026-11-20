package de.htwg.se.balatro.model

import de.htwg.se.balatro.util.RoundError

import scala.util.{Failure, Random, Success, Try}


case class Round(handcards: List[Card], discardCounter: Int = 0, handCounter: Int = 0, selected: List[Int] = List(), deck: Deck = Deck(), score: Int = 0, blind: Blind, rand: Random = Random) {
  val maxPlays = 4
  val maxDiscards = 4

  def remainingPlays: Int = maxPlays - handCounter

  def remainingDiscards: Int = maxDiscards - discardCounter

  def isWon: Boolean = score >= blind.goal

  def isLost: Boolean = remainingPlays == 0

  def currentHand(): PokerHands =
    PokerHands(selectedCards(handcards, selected))


  def select(indices: List[Int]): Round = {
    val newSelected = indices.foldLeft(selected) { (selection, index) =>
      if (selection.contains(index)) {
        selection.filterNot(element => element == index)
      } else {
        index :: selection
      }
    }
    //if over 5 cards are selected, clear all
    copy(selected = if newSelected.size > 5 then List() else newSelected)
  }

  def play(): Try[Round] = {
    if (selected.isEmpty) {
      Failure(RoundError.NoCardsSelected)
    } else if (handCounter >= maxPlays) {
      Failure(RoundError.NoPlaysRemaining)
    } else {
      val pokerHand = PokerHands(selectedCards(handcards, selected))
      val (newHand, newDeck) = changeHands(handcards, selected)
      Success(this.copy(handCounter = handCounter + 1, selected = List(), handcards = newHand, deck = newDeck, score = score + pokerHand.scoreTotal))
    }
  }

  def discard(): Try[Round] = {
    if (discardCounter >= maxDiscards) {
      Failure(RoundError.NoDiscardsRemaining)
    } else if (selected.isEmpty) {
      Failure(RoundError.NoCardsSelected)
    } else {
      val (newHand, newDeck) = changeHands(handcards, selected)
      Success(this.copy(discardCounter = discardCounter + 1, selected = List(), handcards = newHand, deck = newDeck))
    }
  }

  def changeHands(current: List[Card], indices: List[Int]): (List[Card], Deck) =
    val (drawn, newDeck) = deck.draw(rand, indices.length)
    (remainingCards(current, indices) ++ drawn, newDeck)

  def selectedCards(current: List[Card], indices: List[Int]): List[Card] =
    current.zipWithIndex
      .filter { case (_, index) => indices.contains(index) }
      .map { case (card, _) => card }

  def remainingCards(current: List[Card], indices: List[Int]): List[Card] =
    current.zipWithIndex
      .filter { case (_, index) => !indices.contains(index) }
      .map { case (card, _) => card }

}

object Round:
  def start(deck: Deck, blind: Blind, rand: Random = Random): Round = {
    val (handcards, newDeck) = deck.draw(rand, 8)
    Round(handcards = handcards, deck = newDeck, blind = blind, rand = rand)
  }
