package de.htwg.se.balatro

import de.htwg.se.balatro.Action.*
import de.htwg.se.balatro.BlindType.*

case class Game(player: Player = Player(0),
                currentRound: Round = Round(handcards = List(Card(symbol = CardSymbol.HEARTS, rank = CardRank.ACE), Card(symbol = CardSymbol.SPADES, rank = CardRank.TWO)),
                  blind = Blind(Small, 3, 100, 1)),
                roundCounter: Int = 1) {

  def handle(action: Action): Game = {
    action match {
      case Discard => copy(currentRound = currentRound.discard())
      case Play => copy(currentRound = currentRound.play())
      case Select(indices) => copy(currentRound = currentRound.select(indices.map(e => e - 1))) //internal its 0 based but user uses 1 based indices
      case _ => println(s"Simulating Game Action $action"); this
    }

  }

}
