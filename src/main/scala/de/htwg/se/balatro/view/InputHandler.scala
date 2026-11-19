package de.htwg.se.balatro.view

import de.htwg.se.balatro.util.Action

trait InputHandler {
  def handle(input: String): Action
}
