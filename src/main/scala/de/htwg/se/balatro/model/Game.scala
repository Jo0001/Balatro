package de.htwg.se.balatro.model

import de.htwg.se.balatro.model.BlindType.*

import scala.util.{Random, Try}

case class Game(player: Player = Player(0),
                currentRound: Round,
                roundCounter: Int = 1) {
  val ante = 8

  def select(indices: List[Int]): Game = {
    copy(currentRound = currentRound.select(indices))
  }

  def discard(): Try[Game] = {
    currentRound.discard().map(round => copy(currentRound = round))
  }

  def play(): Try[Game] = {
    currentRound.play().map(round => copy(currentRound = round))
  }

  def nextRound(): Game = {
    val blind = currentRound.blind
    val (nextType, nextAnte) = blind.blindType match
      case Small => (Big, blind.ante)
      case Big => (Boss, blind.ante)
      case Boss => (Small, blind.ante + 1)
    val newRound = Round.start(Deck(), Blind(nextType, blind.reward + 1, (blind.goal * 1.5).toInt, nextAnte), currentRound.rand)
    copy(player = Player(player.money + blind.reward), currentRound = newRound, roundCounter = roundCounter + 1)
  }
}

object Game:
  def start(rand: Random = Random): Game =
    val deck = Deck()
    val round = Round.start(deck, Blind(Small, 3, 100, 1), rand)
    Game(currentRound = round)
