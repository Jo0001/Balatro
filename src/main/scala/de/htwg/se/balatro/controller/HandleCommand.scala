package de.htwg.se.balatro.controller

import de.htwg.se.balatro.util.*
import de.htwg.se.balatro.model.Game
import scala.util.{Success, Failure}

class HandleCommand(controller: Controller, action: Action) extends Command {
  private var before: (Game, GameState) = _
  private var after: (Game, GameState) = _
  //auch nicht ohne var möglich sonst kennen undoStep und redoStep keine Neureungen

  override def doStep(): Unit = {
    before = (controller.currentGame, controller.currentState)
    controller.currentState.handle(action, controller.currentGame) match {
      case Success(result) =>
        after = result
        setHelper(after)
      case Failure(error: RoundError) =>
        after = before
        controller.lastError = Some(error)
      case Failure(other) =>
        throw other
    }
  }

  override def undoStep(): Unit = setHelper(before)

  override def redoStep(): Unit = setHelper(after)

  def setHelper(s: (Game, GameState)): Unit = {
    controller.currentGame = s._1
    controller.currentState = s._2
  }
}