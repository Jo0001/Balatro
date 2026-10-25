package de.htwg.se.balatro.ui

import de.htwg.se.balatro.Action
import de.htwg.se.balatro.Action.*

class Parser {

  def parse(input: String): Action = {
    val cleanInput = input.toLowerCase.strip().replaceAll("\\s+", " ") //remove all unnecessary whitespaces
    cleanInput match {
      case in if in.startsWith("select ") => parseSelect(cleanInput)
      case "play" => Play
      case "discard" => Discard
      case "menu" => OpenMenu
      case _ => UnknownCommand
    }
  }

  def parseSelect(input: String): Action =
    val parts = input.split("select")
    if (parts.length >= 2) {
      val numbers = parts.apply(1).strip().split(" ").filter(e => e.toIntOption.isDefined).map(e => e.toInt)
        .filter(e => (e >= 1 && e <= 8)).toList
      if (numbers.isEmpty) {
        UnknownCommand
      } else {
        Select(numbers)
      }
    } else {
      UnknownCommand
    }

}
