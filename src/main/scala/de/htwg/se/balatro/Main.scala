package de.htwg.se.balatro

import de.htwg.se.balatro.controller.Controller
import de.htwg.se.balatro.view.TUI

@main def launchMain(): Unit = {
  val controller = Controller()
  val tui = TUI(controller)
  tui.run()
}