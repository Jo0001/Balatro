package de.htwg.se.balatro

import de.htwg.se.balatro.BlindType.Small

import scala.language.postfixOps

case class Round(handcards: List[Card], discardCounter: Int = 0, handCounter: Int = 0, selected: List[Int] = List()) {
  //todo Blind, Deck & Handcars, discard counter etc

  val blind = Blind(Small, 3, 100, 1)
  val currentScore = 30
  val deck = Deck()

  def select(indices: List[Int]): Round = {
    val newSelected = indices.foldLeft(selected) { (selection, index) =>
      if (selection.contains(index)) {
        selection.filterNot(element => element == index)
      } else if (selection.size < 5) {
        index :: selection
      } else {
        selection
      }
    }
    copy(selected = newSelected)
  }

  def play(): Round = {
    if (selected.isEmpty) {
      //todo handle empty selection
      this
    } else if (handCounter >= 3) {
      //todo
      this
    } else {
      //todo handle points
      this.copy(handCounter = handCounter + 1, selected = List(), handcards = changeHands(handcards, selected))
    }
  }

  def discard(): Round = {
    if (discardCounter == 3) {
      //todo handle if discard is no longer allowed
      this
    } else if (selected.isEmpty) {
      //todo handle empty selection
      this
    } else {
      this.copy(discardCounter = discardCounter + 1, selected = List(), handcards = changeHands(handcards, selected))
    }
  }

  def changeHands(current: List[Card], indices: List[Int]): List[Card] =
    val cleanedHand = current.zipWithIndex.collect {
      case (name, index) if !indices.contains(index) => name
    }
    cleanedHand ++ deck.draw(indices.length)

}
