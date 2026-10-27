package de.htwg.se.balatro

import scala.util.Random

case class Deck(remove: Option[List[Card]] = None):
  val deck: List[Card] =
    for {
      r <- CardRank.values.toList
      v <- CardSymbol.values.toList
      card = Card(v, r)
      if !remove.exists(_.contains(card))
    } yield card

  def draw(count: Int, deck: Deck): (List[Card], Deck) = {
    //todo replace with something random
    val drawn: List[Card] = Random.shuffle(deck.deck).take(count)
    val newdeck = Deck(Some(drawn))
    (drawn, newdeck)
  }
    


