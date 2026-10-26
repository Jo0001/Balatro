package de.htwg.se.balatro


case class Round(handcards: List[Card], discardCounter: Int = 0, handCounter: Int = 0, selected: List[Int] = List(), deck: Deck = Deck(), score: Int = 0, blind: Blind) {

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
      val (newHand, newDeck) = changeHands(handcards, selected)
      this.copy(handCounter = handCounter + 1, selected = List(), handcards = newHand, deck = newDeck)
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
      val (newHand, newDeck) = changeHands(handcards, selected)
      this.copy(discardCounter = discardCounter + 1, selected = List(), handcards = newHand, deck = newDeck)
    }
  }

  def changeHands(current: List[Card], indices: List[Int]): (List[Card], Deck) =
    val cleanedHand = current.zipWithIndex.collect {
      case (name, index) if !indices.contains(index) => name
    }
    val (drawn, newDeck) = deck.draw(indices.length)
    (cleanedHand ++ drawn, newDeck)

}
