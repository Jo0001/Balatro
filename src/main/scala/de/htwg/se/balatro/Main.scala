package de.htwg.se.balatro

import de.htwg.se.balatro.controller.Controller
import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.util.{FileGameStorageStrategy, MockGameStorageStrategy}
import de.htwg.se.balatro.view.TUI

@main def launchMain(): Unit = {
  val game = Game.start()
  val controller = Controller(game, new FileGameStorageStrategy())
  val tui = TUI(controller)
  tui.run()
}