package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.util.Action

class GameOverState extends GameState {

  def handle(action: Action, game: Game): (Game, GameState) = ??? //todo

}
