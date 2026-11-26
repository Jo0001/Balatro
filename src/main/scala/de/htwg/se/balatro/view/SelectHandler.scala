package de.htwg.se.balatro.view

import de.htwg.se.balatro.util.Action.Select
import de.htwg.se.balatro.util.Result.*
import de.htwg.se.balatro.util.{Action, Result}

class SelectHandler(next: InputHandler) extends InputHandler {

  def handle(input: String): Result[Action] =
    if (input.startsWith("select ")) parseSelect(input)
    else next.handle(input)

  def parseSelect(input: String): Result[Action] =
    for
      argument <- selectArgument(input)
      numbers  <- cardNumbers(argument)
    yield Select(numbers.map(_ - 1))

  private def selectArgument(input: String): Result[String] = {
    val parts = input.split("select")
    if (parts.length == 2) Ok(parts.apply(1).strip())
    else Err("Use 'select <cardnumber> ...' to select cards")
  }

  private def cardNumbers(argument: String): Result[List[Int]] = {
    val numbers = argument.split(" ").filter(e => e.toIntOption.isDefined).map(e => e.toInt)
      .filter(e => (e >= 1 && e <= 8)).toList
    if (numbers.isEmpty) Err("Card numbers must be between 1 and 8")
    else Ok(numbers)
  }
}
