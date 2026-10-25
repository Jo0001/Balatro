package de.htwg.se.balatro

case class Deck(cards: List[Card]= List.tabulate(CardSymbol.values.length,Rank.values.length)((x,y) => Card(CardSymbol.fromOrdinal(x),Rank.fromOrdinal(y))):
  for(x <- CardSymbol.values) {
    for(y <- Rank.values) {
      deck = Card(x, y) :: deck
    }
  }
    


