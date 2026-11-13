package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.util.Action
import de.htwg.se.balatro.util.Action.*

class PlayingState extends GameState {
  override def handle(action: Action, game: Game): (Game, GameState) = action match {
    case Select(indices) => (game.select(indices), this)
    case Discard => (game.discard(), this)
    //todo case OpenMenu => (game, MenuState(previous = this))
    case Play =>
      val next = game.play()
      val round = next.currentRound
      if round.score >= round.blind.goal then (next, RoundWonState())
      else if round.remainingPlays == 0 then (next, GameOverState())
      else (next, this)
    case _ => (game, this)
  }
}
