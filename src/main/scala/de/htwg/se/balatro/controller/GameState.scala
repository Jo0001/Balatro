package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.util.*

import scala.util.Try

trait GameState {
  def phase: Phase
  def handle(action: Action, game: Game): Try[(Game, GameState)]
}
