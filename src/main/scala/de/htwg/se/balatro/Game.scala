package de.htwg.se.balatro

import de.htwg.se.balatro.Action.*

case class Game(player: Player = Player(0),
                currentRound: Round = Round(handcards = List(Card(symbol = CardSymbol.HEARTS, rank = Rank.ACE), Card(symbol = CardSymbol.SPADES, rank = Rank.TWO))),
                roundCounter: Int = 1) {

  def handle(action: Action): Game = {
    action match {
      case Discard => copy(currentRound = currentRound.discard())
      case _ => println(s"Simulating Game Action $action"); this
    }

  }

}
