package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.util.{Action, Phase}
import de.htwg.se.balatro.util.Action.Next

class RoundWonState extends GameState {
  def phase: Phase = Phase.RoundWon
  def handle(action: Action, game: Game): (Game, GameState) = action match {
    case Next => (game.nextRound(), PlayingState())
    case _ => (game, this)
  }
}
