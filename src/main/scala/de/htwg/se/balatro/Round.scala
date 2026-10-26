package de.htwg.se.balatro

import de.htwg.se.balatro.BlindType.Small

import scala.language.postfixOps

case class Round(handcards: List[Card], discardCounter: Int = 0, selected: List[Int] = List()) {
  //todo Blind, Deck & Handcars, discard counter etc

  val blind = Blind(Small, 3, 100, 1)
  val currentScore = 30

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
    }
    //todo handle points
    this
  }

  def discard(): Round = {
    if (discardCounter == 3) {
      //todo handle if discard is no longer allowed
    }
    //todo only if something is selected
    //todo properly refresh from deck
    //todo clear selected
    this.copy(discardCounter = discardCounter + 1, handcards = List(Card(symbol = CardSymbol.SPADES, rank = CardRank.SIX), Card(symbol = CardSymbol.SPADES, rank = CardRank.QUEEN)))
  }

}
