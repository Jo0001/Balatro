package de.htwg.se.balatro.ui

import de.htwg.se.balatro.controller.Controller
import de.htwg.se.balatro.util.Action.UnknownCommand
import de.htwg.se.balatro.model.Game

import scala.io.StdIn.readLine
import de.htwg.se.balatro.view.{Parser, TUI}

import scala.annotation.tailrec

//Main has no testable logic
// $COVERAGE-OFF$
@main def launchMain(): Unit = {
  val tui = TUI()
  val controller = Controller()
  val parser = Parser()
  val game = Game.start()
  println(tui.createRender(game))
  print(">")
  runloop(tui, parser, game)
}

@tailrec
def runloop(tui: TUI, parser: Parser, game: Game): Game =

  val line = readLine()
  val action = parser.parse(line)
  if (action == UnknownCommand) {
    println(tui.createCommandErrorMessage(line))
    print(">")
    runloop(tui, parser, game)
  } else {
    val newGame = game.handle(action)
    println(tui.createRender(newGame))
    print(">")
    runloop(tui, parser, newGame)
  }
// $COVERAGE-ON$