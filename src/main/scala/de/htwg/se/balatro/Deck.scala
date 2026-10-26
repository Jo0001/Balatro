package de.htwg.se.balatro

case class Deck():
  val deck: List[Card] =
    for {
      r <- CardRank.values.toList
      v <- CardSymbol.values.toList
    } yield Card(v, r)

  def draw(count: Int): (List[Card], Deck) =
    //todo replace with something random
    (deck.take(count), this)
    


