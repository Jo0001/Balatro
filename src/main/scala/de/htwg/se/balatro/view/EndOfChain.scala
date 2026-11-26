package de.htwg.se.balatro.view

import de.htwg.se.balatro.util.Result.*
import de.htwg.se.balatro.util.{Action, Result}

class EndOfChain extends InputHandler {
  def handle(input: String): Result[Action] =
    Err(s"Unknown or misspelled command '$input'")
}
  
