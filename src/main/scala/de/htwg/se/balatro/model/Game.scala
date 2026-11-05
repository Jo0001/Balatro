package de.htwg.se.balatro.model

import de.htwg.se.balatro.model.BlindType.*

import scala.util.Random

case class Game(player: Player = Player(0),
                currentRound: Round,
                roundCounter: Int = 1) {
  val ante = 8

  def select(indices: List[Int]): Game = {
  copy(currentRound = currentRound.select(indices))
  }

  def discard(): Game = {
  copy(currentRound = currentRound.discard())
  }

  def play() : Game = {
  copy(currentRound = currentRound.play())
  }
}

object Game:
  def start(rand: Random = Random): Game =
    val deck = Deck()
    val round = Round.start(deck, Blind(Small, 3, 100, 1), rand)
    Game(currentRound = round)
