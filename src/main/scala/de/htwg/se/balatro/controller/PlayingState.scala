package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.util.{Action, Phase}
import de.htwg.se.balatro.util.Action.*

import scala.util.{Success, Try}

class PlayingState extends GameState {
  def phase: Phase = Phase.Playing

  // PlayingState.scala
  def handle(action: Action, game: Game): Try[(Game, GameState)] = action match {
    case Select(indices) => Success((game.select(indices), this))
    case Discard => game.discard().map(next => (next, this))
    case Play =>
      game.play().map { next =>
        val round = next.currentRound
        if round.score >= round.blind.goal then (next, RoundWonState())
        else if round.remainingPlays == 0 then (next, GameOverState())
        else (next, this)
      }
    case _ => Success((game, this))
  }
}
