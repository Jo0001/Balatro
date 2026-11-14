package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.util.{Action, Phase}

class GameOverState extends GameState {
  def phase: Phase = Phase.GameOver

  def handle(action: Action, game: Game): (Game, GameState) = ??? //todo

}
