package de.htwg.se.balatro.view

import de.htwg.se.balatro.util.{Action, Result}

trait InputHandler {
  def handle(input: String): Result[Action]
}
