package de.htwg.se.balatro.view

import de.htwg.se.balatro.util.Result.*
import de.htwg.se.balatro.util.{Action, Result}

abstract class ChainHandler(next: InputHandler) extends InputHandler {
  def tryHandle(input: String): Option[Action]

  def handle(input: String): Result[Action] =
    tryHandle(input) match {
      case Some(action) => Ok(action)
      case None         => next.handle(input)
    }
}
