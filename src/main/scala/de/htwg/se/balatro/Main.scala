package de.htwg.se.balatro

import de.htwg.se.balatro.controller.Controller
import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.view.{SocketServer, TUI, TUIRemote}

@main def launchMain(): Unit = {
  val game = Game.start()
  val controller = Controller(game)
  val server = SocketServer(controller = controller, port = 25565)
  server.start()
  val tui = TUI(controller)
  tui.run()
}