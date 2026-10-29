package de.htwg.se.balatro.ui

import de.htwg.se.balatro.Action.UnknownCommand

import scala.io.StdIn.readLine
import de.htwg.se.balatro.Game

import scala.annotation.tailrec

//Main has no testable logic
// $COVERAGE-OFF$
@main def launchMain(): Unit = {
  val tui = TUI()
  val parser = Parser()
  val game = Game.start()
  println(tui.createRender(game))
  runloop(tui, parser, game)
}

@tailrec
def runloop(tui: TUI, parser: Parser, game: Game): Game =

  val line = readLine()
  val action = parser.parse(line)
  if (action == UnknownCommand) {
    println(tui.createCommandErrorMessage(line))
    runloop(tui, parser, game)
  } else {
    val newGame = game.handle(action)
    println(tui.createRender(newGame))
    runloop(tui, parser, newGame)
  }
// $COVERAGE-ON$