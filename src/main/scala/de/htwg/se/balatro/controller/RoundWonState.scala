package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.util.{Action, Phase}
import de.htwg.se.balatro.util.Action.Next
import scala.util.{Try, Success}

class RoundWonState extends GameState {
  def phase: Phase = Phase.RoundWon

  def handle(action: Action, game: Game): Try[(Game, GameState)] = action match {
    case Next => Success((game.nextRound(), PlayingState()))
    case _ => Success((game, this))
  }
}
