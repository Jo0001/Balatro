package de.htwg.se.balatro

case class Player(score: Int) {
  def setScore(newScore: Int): Player = Player(newScore)
}

