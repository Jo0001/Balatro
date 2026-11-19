package de.htwg.se.balatro.view

import de.htwg.se.balatro.util.Action.*
import de.htwg.se.balatro.util.Action

class Parser {
  val h5 = KeywordHandler("restart", Restart, EndOfChain())
  val h4 = KeywordHandler("next", Next, h5)
  val h3 = KeywordHandler("discard", Discard, h4)
  val h2 = KeywordHandler("play", Play, h3)
  val h1 = SelectHandler(h2)
  val chain = h1

  def parse(input: String): Action = {
    val cleanInput = input.toLowerCase.strip().replaceAll("\\s+", " ") //remove all unnecessary whitespaces
    chain.handle(cleanInput)
  }

}
