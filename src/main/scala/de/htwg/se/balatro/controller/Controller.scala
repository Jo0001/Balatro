package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.util.{Action, Observable}
import de.htwg.se.balatro.util.Action.*

class Controller extends Observable {

  val game = Game.start()

  def handle(action: Action): Unit = {
    action match {
      case Discard => game.copy(currentRound = game.currentRound.discard()); notifyObservers()
      case Play => game.copy(currentRound = game.currentRound.play()); notifyObservers()
      case Select(indices) => game.copy(currentRound = game.currentRound.select(indices.map(e => e - 1))); notifyObservers() //internal its 0 based but user uses 1 based indices
      case _ => println(s"Simulating Game Action $action"); this
    }
  }

}