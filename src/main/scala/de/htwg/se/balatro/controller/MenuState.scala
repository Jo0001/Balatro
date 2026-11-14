package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.util.{Action, Phase}

class MenuState extends GameState {
  def phase: Phase = Phase.Menu
  override def handle(action: Action, game: Game): (Game, GameState) = ??? //todo
}
