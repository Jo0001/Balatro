package de.htwg.se.balatro

import scala.util.Random


case class Round(handcards: List[Card], discardCounter: Int = 0, handCounter: Int = 0, selected: List[Int] = List(), deck: Deck = Deck(), score: Int = 0, blind: Blind, rand: Random = Random) {
  val maxPlays = 4
  val maxDiscards = 4

  def currentHand(): PokerHands =
    if (selectedCards(handcards, selected).nonEmpty) {
      PokerHands(selectedCards(handcards, selected))
    } else {
      PokerHands(List()) //todo not finished
    }

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

  def play(): Round = {
    if (selected.isEmpty) {
      //todo handle empty selection
      this
    } else if (handCounter >= maxPlays) {
      //todo
      this
    } else {
      val pokerHand = PokerHands(selectedCards(handcards, selected))
      val (newHand, newDeck) = changeHands(handcards, selected)
      this.copy(handCounter = handCounter + 1, selected = List(), handcards = newHand, deck = newDeck, score = score + pokerHand.scoreTotal)
    }
  }

  def discard(): Round = {
    if (discardCounter >= maxDiscards) {
      //todo handle if discard is no longer allowed
      this
    } else if (selected.isEmpty) {
      //todo handle empty selection
      this
    } else {
      val (newHand, newDeck) = changeHands(handcards, selected)
      this.copy(discardCounter = discardCounter + 1, selected = List(), handcards = newHand, deck = newDeck)
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
