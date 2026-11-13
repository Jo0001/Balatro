package de.htwg.se.balatro.controller

import de.htwg.se.balatro.model.Game
import de.htwg.se.balatro.util.*

class Controller(startGame: Game = Game.start()) extends Observable {
  var currentGame: Game = startGame
  var currentState: GameState = PlayingState()

  def gameSnapshot: GameSnapshot = GameSnapshot(
    blindName = currentGame.currentRound.blind.blindType.toString,
    score = currentGame.currentRound.score,
    goal = currentGame.currentRound.blind.goal,
    reward = currentGame.currentRound.blind.reward,
    ante = currentGame.currentRound.blind.ante,
    maxAnte = currentGame.ante,
    roundCounter = currentGame.roundCounter,
    hands = currentGame.currentRound.remainingPlays,
    discards = currentGame.currentRound.remainingDiscards,
    chips = currentGame.currentRound.currentHand().chips,
    multiplier = currentGame.currentRound.currentHand().mult,
    chipsTotal = currentGame.currentRound.currentHand().chipsTotal,
    handName = currentGame.currentRound.currentHand().hand.toString,
    handcards = createHandCards(),
    phase = currentState.phase //todo display on tui if required
  )

  def handle(action: Action): Unit = {
    val (game, state) = currentState.handle(action, currentGame)
    currentGame = game
    currentState = state
    notifyObservers()
  }

  def createHandCards(): List[CardSnapshot] = {
    currentGame.currentRound.handcards.zipWithIndex.map { (card, index) =>
      CardSnapshot(selected = currentGame.currentRound.selected.contains(index), rank = card.rank.toString, symbol = card.symbol, color = card.color)
    }
  }

}