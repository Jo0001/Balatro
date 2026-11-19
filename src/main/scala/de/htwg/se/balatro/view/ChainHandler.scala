package de.htwg.se.balatro.view

import de.htwg.se.balatro.util.Action

abstract class ChainHandler(next: InputHandler) extends InputHandler {
  def tryHandle(input: String): Option[Action]

  def handle(input: String): Action = {
    tryHandle(input).getOrElse(next.handle(input))
  }
}
