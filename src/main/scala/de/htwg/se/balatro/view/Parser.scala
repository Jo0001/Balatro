package de.htwg.se.balatro.view

import de.htwg.se.balatro.util.Action.*
import de.htwg.se.balatro.util.Action

class Parser {
  val h5 = KeywordHandler("restart", Restart, EndOfChain) // letztes Glied, Nachfolger = Kettenende
  val h4 = KeywordHandler("next", Next, h5)
  val h3 = KeywordHandler("discard", Discard, h4)
  val h2 = KeywordHandler("play", Play, h3)
  val h1 = SelectHandler(h2) // erstes Glied
  val chain = h1

  def parse(input: String): Action = {
    val cleanInput = input.toLowerCase.strip().replaceAll("\\s+", " ") //remove all unnecessary whitespaces
    chain.handle(cleanInput)
  }

  def parseSelect(input: String): Action =
    val parts = input.split("select")
    if (parts.length == 2) {
      val numbers = parts.apply(1).strip().split(" ").filter(e => e.toIntOption.isDefined).map(e => e.toInt)
        .filter(e => (e >= 1 && e <= 8)).toList
      if (numbers.isEmpty) {
        UnknownCommand
      } else {
        Select(numbers.map(_ - 1))
      }
    } else {
      UnknownCommand
    }

}
