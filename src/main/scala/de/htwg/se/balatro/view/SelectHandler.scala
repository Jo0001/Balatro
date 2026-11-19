package de.htwg.se.balatro.view

import de.htwg.se.balatro.util.Action
import de.htwg.se.balatro.util.Action.*

class SelectHandler(next: InputHandler) extends ChainHandler(next) {
  def tryHandle(input: String): Option[Action] = {
    Option.when(input.startsWith("select "))(parseSelect(input))
  }

  def parseSelect(input: String): Action = {
    val parts = input.split("select")
    val numbers = parts.apply(1).strip().split(" ").filter(e => e.toIntOption.isDefined).map(e => e.toInt)
      .filter(e => (e >= 1 && e <= 8)).toList
    if (numbers.isEmpty) {
      UnknownCommand
    } else {
      Select(numbers.map(_ - 1))
    }
  }
}
