package de.htwg.se.balatro.ui

import de.htwg.se.balatro.Action.UnknownCommand

import scala.io.StdIn.readLine
import de.htwg.se.balatro.Game

// $COVERAGE-OFF$
@main def launchMain(): Unit = {
  val tui = TUI()
  val parser = Parser()
  var game = Game() //todo change later to propper val

  println(tui.createRender(game))
  while (true) {
    val line = readLine()
    val action = parser.parse(line)
    if (action == UnknownCommand) {
      println(tui.createCommandErrorMessage(line))
    } else {
      game = game.handle(action)
      //todo maybe better in TUI
      //only works with ANSI capable console, not in IntelliJ
      print("\u001b[2J\u001b[H")
      Console.flush()
      println(tui.createRender(game))
    }
  }

}
// $COVERAGE-ON$