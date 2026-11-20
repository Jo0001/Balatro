package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.util.Action.Restart
import de.htwg.se.balatro.util.{Action, Phase}
import scala.util.{Try, Success}

class GameOverState extends GameState {
  def phase: Phase = Phase.GameOver

  def handle(action: Action, game: Game): Try[(Game, GameState)] = action match {
    case Restart => Success((Game.start(), PlayingState()))
    case _ => Success((game, this))
  }
}