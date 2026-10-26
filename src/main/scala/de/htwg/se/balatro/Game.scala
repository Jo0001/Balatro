package de.htwg.se.balatro

import de.htwg.se.balatro.Action.*

case class Game(player: Player = Player(0),
                currentRound: Round = Round(handcards = List(Card(symbol = CardSymbol.HEARTS, rank = CardRank.ACE), Card(symbol = CardSymbol.SPADES, rank = CardRank.TWO))),
                roundCounter: Int = 1) {

  def handle(action: Action): Game = {
    action match {
      case Discard => copy(currentRound = currentRound.discard())
      case Select(indices) => copy(currentRound = currentRound.select(indices.map(e => e - 1))) //internal its 0 based but user uses 1 based indices
      case _ => println(s"Simulating Game Action $action"); this
    }

  }

}
