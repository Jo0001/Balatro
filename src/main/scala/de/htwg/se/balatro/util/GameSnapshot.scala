package de.htwg.se.balatro.util

case class GameSnapshot(blindName: String, score: Int, goal: Int, reward: Int, ante: Int, maxAnte: Int, roundCounter: Int, hands: Int, discards: Int, chips: Int, multiplier: Int, chipsTotal: Int, handName: String, handcards: List[CardSnapshot]) {
}
