package de.htwg.se.balatro.util

import de.htwg.se.balatro.model.Game
import scala.util.Try

trait GameStorageStrategy {
  def save(game: Game): Try[Unit]

  def load(): Try[Game]
}