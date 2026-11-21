package de.htwg.se.balatro.util

import de.htwg.se.balatro.model.Game

import scala.util.{Failure, Success, Try}

class MockGameStorageStrategy(initial: Option[Game] = None) extends GameStorageStrategy {
  private var stored: Option[Game] = initial
  def save(game: Game): Try[Unit] = { stored = Some(game); Success(()) }
  def load(): Try[Game] = stored.map(Success(_)).getOrElse(Failure(new NoSuchElementException("no gamestate")))
}