package de.htwg.se.balatro.ui

import de.htwg.se.balatro.controller.Controller
import de.htwg.se.balatro.view.TUI

@main def launchMain(): Unit = {
  val controller = Controller()
  val tui = TUI(controller)
  controller.add(tui)
  tui.run()
}