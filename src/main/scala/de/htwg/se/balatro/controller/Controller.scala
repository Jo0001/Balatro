package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.util.{Action, Observable}
import de.htwg.se.balatro.util.Action.*

class Controller(startGame: Game = Game.start()) extends Observable {
  
  var currentGame: Game = startGame //todo avoid var

  def game: Game = currentGame//todo avoid Game from Lower model layer

  def handle(action: Action): Unit = {
    currentGame = action match {
      case Discard => currentGame.copy(currentRound = currentGame.currentRound.discard())
      case Play => currentGame.copy(currentRound = currentGame.currentRound.play())
      //internal its 0 based but user uses 1 based indices
      case Select(indices) => currentGame.copy(currentRound = currentGame.currentRound.select(indices.map(e => e - 1)))
      case _ => currentGame //todo OpenMenu (and everything else) does not change the model yet
    }
    notifyObservers()
  }

}