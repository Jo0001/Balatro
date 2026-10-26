package de.htwg.se.balatro

case class Deck():
  val deck: List[Card] =
    for {
      r <- CardRank.values.toList
      v <- CardSymbol.values.toList
  } yield Card(v, r)
    


