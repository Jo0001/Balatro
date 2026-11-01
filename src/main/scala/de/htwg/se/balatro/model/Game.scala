package de.htwg.se.balatro.model

import de.htwg.se.balatro.util.Action.*
import BlindType.*
import de.htwg.se.balatro.util.Action

import scala.util.Random

//Game currently only calls other classes with no logic on itself so its currently not tested
// $COVERAGE-OFF$
case class Game(player: Player = Player(0),
                currentRound: Round,
                roundCounter: Int = 1) {

  def handle(action: Action): Game = {
    action match {
      case Discard => copy(currentRound = currentRound.discard())
      case Play => copy(currentRound = currentRound.play())
      case Select(indices) => copy(currentRound = currentRound.select(indices.map(e => e - 1))) //internal its 0 based but user uses 1 based indices
      case _ => println(s"Simulating Game Action $action"); this
    }
  }
  // $COVERAGE-ON$
}

object Game:
  def start(rand: Random = Random): Game =
    val deck = Deck()
    val round = Round.start(deck, Blind(Small, 3, 100, 1), rand)
    Game(currentRound = round)
