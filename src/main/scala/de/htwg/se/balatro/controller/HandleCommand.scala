package de.htwg.se.balatro.controller

import de.htwg.se.balatro.util.*
import de.htwg.se.balatro.model.Game

class HandleCommand(controller: Controller, action: Action) extends Command {
  private var before: (Game, GameState) = _
  private var after: (Game, GameState) = _
  //auch nicht ohne var möglich sonst kennen undoStep und redoStep keine Neureungen
  
  override def doStep(): Unit = {
    before = (controller.currentGame, controller.currentState)
    after = controller.currentState.handle(action, controller.currentGame)
    set(after)
  }
  override def undoStep(): Unit = {
    set(before)
  }

  override def redoStep(): Unit = {
    set(after)
  }

  private def set(s: (Game, GameState)): Unit = {
    controller.currentGame = s._1
    controller.currentState = s._2
  }
}
